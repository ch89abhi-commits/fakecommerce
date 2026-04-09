package com.example.fakecom.services.ProductServices.V1_Service;

import java.util.List;

import com.example.fakecom.schema.ProductSchema;

public interface ReadService {

    public ProductSchema getById(Long id);

    public List<ProductSchema> getAll();
    
    
}
