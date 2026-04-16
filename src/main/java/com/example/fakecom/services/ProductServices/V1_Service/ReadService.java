package com.example.fakecom.services.ProductServices.V1_Service;

import java.util.List;
import java.util.Map;

import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.schema.ProductSchema;

public interface ReadService {

    public ProductSchema getById(Long id);

    public List<ProductSchema> getAll();

    public List<ProductSchema> categorySearch(String category);

    public List<Map<String,Object>> allCategories();

    
    
}
