package com.example.fakecom.services.CategoryServices;

 
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.DTOs.ResponseDTOs.CategoryResponse;
import com.example.fakecom.Exceptions.NoUserExistException;
import com.example.fakecom.ObjectMappers.CategoryMapper;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.V1_Category.Read_Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService implements Read_Service {
 
    private   final CategoryRepository categoryRepository;
    private  final CategoryMapper categoryMapper;



    @Override
    public List<CategorySchema> gettingAllCategory(){

        return  categoryRepository.findAll();

    }


    @Override
    public CategoryResponse addingCategory(CategoryRequest data){

        CategorySchema ct=categoryMapper.DTOtoSchema(data);
        
        return categoryMapper.SchemaToResponse(
                      categoryRepository.save(ct)
        );

    }

    // finding the category by the id just the read operations
    
    @Override
    public CategoryRequest getCategory(Long id){

        return categoryMapper.SchemaToDTO(
                        categoryRepository.findById(id).orElseThrow(()-> new NoUserExistException("no category exist")) 
        )   ;
    }

    @Override
    public CategorySchema categoryDelete(Long id){
        CategorySchema ct= categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(ct);
        return ct;

    }



}
