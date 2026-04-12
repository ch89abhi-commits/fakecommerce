package com.example.fakecom.ObjectMapperUtils;

import org.springframework.stereotype.Component;

import com.example.fakecom.DTOs.RequestDTOs.CompleteProductRequest;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
 

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// this is the layer that have the data base connection and only called by the mapper class to create object 

@Component
@RequiredArgsConstructor
public class CategoryCheck {

    private final CategoryRepository categoryRepository;
   

    @Transactional
    public CategorySchema checkOrReturnCategory(CompleteProductRequest dto){
        return categoryRepository.findById(dto.getProductCategoryId()).orElseGet(()->{
                                        return categoryRepository.save(CategorySchema.builder().name(dto.getCategoryName()).build());
        });



    }
   

    
}
