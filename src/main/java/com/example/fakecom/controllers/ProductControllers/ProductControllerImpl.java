package com.example.fakecom.controllers.ProductControllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakecom.DTOs.RequestDTOs.CompleteProductRequest;
import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;
import com.example.fakecom.Utility.ClientResponse;
import com.example.fakecom.controllers.ProductControllers.V1_Controllers.ReadProducts;
import com.example.fakecom.controllers.ProductControllers.V1_Controllers.WriteProducts;
import com.example.fakecom.schema.ProductSchema;
import com.example.fakecom.services.ProductServices.ProductService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductControllerImpl implements ReadProducts, WriteProducts  {
     
    private final  ProductService productservice;

     

    // getting the list of all the products
        @Override
        public ResponseEntity<ClientResponse<List<ProductSchema>>> getAllProducts() {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                                                                ClientResponse
                                                                        .SuccessResponse(HttpStatus.ACCEPTED.value(), productservice.getAll() ));
        }

        // reading by the ids
        @Override
        public ResponseEntity<ClientResponse<ProductSchema>> getById(Long id){
            return ResponseEntity
                            .status(HttpStatus.ACCEPTED)
                            .body(
                                ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(), 
                                productservice.getById(id))
                            );
                            
        }


    @Override
    public ResponseEntity<ClientResponse<DetailProductResponse>>  addNewProduct(DetailProductRequest data){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ClientResponse.SuccessResponse(HttpStatus.CREATED.value(),productservice.createProduct(data) ));
        

    }

    @Override
    public ResponseEntity<ClientResponse<DetailProductResponse>> updateProduct( Long id , DetailProductRequest data){

        return ResponseEntity
                            .status(HttpStatus.ACCEPTED)
                            .body(
                                    ClientResponse
                                            .SuccessResponse( HttpStatus.ACCEPTED.value(), productservice.updateProduct(id, data) )
                                    );



}


@Override
public ResponseEntity<ClientResponse<Object>> deleteProduct(Long id){
    return         ResponseEntity.status(HttpStatus.ACCEPTED).body(
                                            ClientResponse
                                                .SuccessResponse(HttpStatus.ACCEPTED.value(), productservice.deleteProductEntity(id))) ;
}



   
@Override
public ResponseEntity<ClientResponse<Object>> addcompleteProduct( CompleteProductRequest data){


    return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(),
                        productservice.createCompleteProduct(data)        
                )
    );


}

@Override
public ResponseEntity<ClientResponse<Object>> searchByCategory( String category) throws Exception{
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(),
    productservice.categorySearch(category)        
));

}


@Override
public ResponseEntity<ClientResponse<Object>> categories(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(),
        productservice.allCategories()
    ));
}

     
    
    
}
