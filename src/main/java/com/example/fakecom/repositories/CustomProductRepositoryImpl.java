package com.example.fakecom.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.schema.ProductSchema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CustomProductRepositoryImpl  implements  CustomProductRepository{

    @PersistenceContext
    private EntityManager entityManager; ///it is created form the entity manger factorl

    @Override
    public ProductSchema findId(Long id){

        return entityManager.find(ProductSchema.class,id);
    }

    // @Override
    // public List<ProductSchema> findCategoryBasedProduct(String category){
    //     return entityManager.createQuery(" SELECT p FROM Product p JOIN p.category c WHERE c.categoryName = :category",ProductSchema.class).setProperty("category",category).getResultList();

    // }

    @Override  // jpql query
    public List<ProductSchema> findCategoryBasedProduct(String category){

        return entityManager.createNamedQuery("select p from ProductSchema p join p.category c where c.name= :category", ProductSchema.class).setParameter("category",category).getResultList();
    }

/*
@Override  // sql query
public List<ProductSchema> findCategoryBasedProduct(String category){

string sql ="select * from Products inner join Categories on Products.category_id=Categories.id where categories.name= :category"
    
return entityManager.createNativeQuery(sql, ProductSchema.class).setParameter("category",category).getResultList();
}
*/


    
    
}
