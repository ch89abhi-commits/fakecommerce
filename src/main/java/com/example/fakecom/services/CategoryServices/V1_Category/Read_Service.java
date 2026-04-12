package com.example.fakecom.services.CategoryServices.V1_Category;

import java.util.List;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.schema.CategorySchema;

public interface Read_Service {
    

    public CategoryRequest getCategory(Long id);

    // reading all the categories in the categoru schema

    public List<CategorySchema> gettingAllCategory();


    // wrting the category directly from the category api not fomr the 

    public CategoryRequest addingCategory(CategoryRequest data);

    
}
