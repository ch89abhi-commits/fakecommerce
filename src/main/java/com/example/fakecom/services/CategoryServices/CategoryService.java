package com.example.fakecom.services.CategoryServices;

 
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.ObjectMappers.CategoryMapper;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.services.CategoryServices.V1_Category.Read_Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements Read_Service {
 
    public  final CategoryRepository categoryRepository;
    public final CategoryMapper categoryMapper;



    @Override
    public List<CategorySchema> gettingAllCategory(){

        return  categoryRepository.findAll();

    }


    @Override
    public CategoryRequest addingCategory(CategoryRequest data){

        CategorySchema ct=categoryMapper.DTOtoSchema(data);
        
        return categoryMapper.SchemaToDTO(
                      categoryRepository.save(ct)
        );

    }

    // finding the category by the id just the read operations
    
    @Override
    public CategoryRequest getCategory(Long id){

        return categoryMapper.SchemaToDTO(
                        categoryRepository.findById(id).orElseThrow() 
        )   ;
    }

    @Override
    public CategorySchema categoryDelete(Long id){
        CategorySchema ct= categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(ct);
        return ct;

    }


}
