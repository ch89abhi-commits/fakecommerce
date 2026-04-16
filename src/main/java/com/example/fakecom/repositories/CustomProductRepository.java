package com.example.fakecom.repositories;



import java.util.List;

import com.example.fakecom.schema.ProductSchema;

 


// @RequiredArgsConstructor
public interface CustomProductRepository {

   

    public ProductSchema findId(Long id);

    public List<ProductSchema> findCategoryBasedProduct(String category);
   

    

    
    
}
