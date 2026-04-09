package com.example.fakecom.repositories;

import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.ProductSchema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomProductRepositoryImpl  implements  CustomProductRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductSchema findId(Long id){

        return entityManager.find(ProductSchema.class,id);
    }


    
    
}
