package com.example.fakecom.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.ProductSchema;

@Repository
public interface ProductRepository extends JpaRepository<ProductSchema, Long> ,CustomProductRepository {

@Query(value="SELECT * FROM Products ",nativeQuery=true) // native is for the sql queries;
 public List<ProductSchema> findAllProducts();

    
    
}
