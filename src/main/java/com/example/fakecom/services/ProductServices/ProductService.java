package com.example.fakecom.services.ProductServices;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.fakecom.DTOs.RequestDTOs.CompleteProductRequest;
import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;
import com.example.fakecom.Exceptions.NoUserExistException;
import com.example.fakecom.ObjectMappers.CompleteMapper;
import com.example.fakecom.ObjectMappers.ProductMappers;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.repositories.ProductRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.schema.ProductSchema;
import com.example.fakecom.services.ProductServices.V1_Service.ReadService;
import com.example.fakecom.services.ProductServices.V1_Service.WriteServices;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;




@Service
@RequiredArgsConstructor
public class ProductService implements ReadService ,WriteServices {

    private final ProductRepository productRepository; // injected by the spring ioc container during the component scan
    private final ProductMappers productMapper; // make sure that the this field should not be changed based on the different threads
    private final CompleteMapper completeMapper;
    private final CategoryRepository categoryRepository;



    @Override
    public ProductSchema getById(Long id){
        return productRepository.findById(id).orElseThrow(()-> new NoUserExistException("cannot abkle to find"));
    }

    @Override
    public List<ProductSchema> getAll(){
        
        return productRepository.findAllProducts();
    }


    // here we have implemented the service layer and the data taken by the service layer is used by the mapper construct and return the data..
    @Override
    public DetailProductResponse createProduct(DetailProductRequest data){

        // ProductSchema mr=ProductSchema.builder().description(data.getProductDescription()).image(data.getProductImageDatabaseURL())
        //                         .name(data.getProductName()).rating(data.getProductPrice()).build();

                                // here i have the data key 

        return  productMapper.productSchemaToResponse( productRepository.save(productMapper.productDtoToSchema(data)) );



    }

    @Override
    @Transactional
    public String createCompleteProduct(CompleteProductRequest data){

        System.err.println("Enterred");
        productRepository.save(completeMapper.ProductDTO(data));
        System.err.println("Exit");

return "ok";
    }



    @Override     
    public DetailProductResponse updateProduct(Long id,DetailProductRequest data) {

        ProductSchema oldData=productRepository.findById(id).orElseThrow(()-> new NoUserExistException("user with the corresponding Id not found"));
       
        productMapper.ProductSchemaUpdateFromDTO(data, oldData);

        // here i have updated the data with the old data 

        return productMapper.productSchemaToResponse(productRepository.save(oldData));

    

    }



@Override
    public DetailProductResponse deleteProductEntity(Long id){



        ProductSchema data = productRepository.findById(id).orElseThrow(()-> new NoUserExistException("no resourse found"));

        productRepository.deleteById(id);

        return productMapper.productSchemaToResponse(data);

         

    }



    @Override
    public List<ProductSchema> categorySearch(String category){
// search on the product such that their cateoruf
     
       Optional<CategorySchema> c =categoryRepository.findByName(category);
// here error handleing is required
    if(c.isPresent()){

        return productRepository.findByCategory_id(c.get().getId());

    }
    else{
        throw  new NoUserExistException("no category name exits");
    }


    }
        

    @Override
    public List<Map<String,Object>> allCategories(){

        return productRepository.findAllCategories();

    }


     
 




    
}
