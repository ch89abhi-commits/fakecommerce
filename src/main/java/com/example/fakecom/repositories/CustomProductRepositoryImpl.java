package com.example.fakecom.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

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
    
 
    // @Override  // jpql query
    // public List<ProductSchema> findCategoryBasedProduct(String category){

    //     String name="SELECT p from ProductSchema p JOIN p.category c WHERE c.name= :category";
    //     TypedQuery<ProductSchema> query=entityManager.createQuery(name,ProductSchema.class);

    //     query.setParameter("category", category);
    //     return query.getResultList();
    //     // return entityManager.createQuery(, ProductSchema.class).setParameter("category",category).getResultList();
    // }
 

@Override  // sql query
public  List<ProductSchema> findCategoryBasedProduct(String category){

String sql ="select p.* from Products p inner join Categories c on p.category_id=c.id where c.name= :category";
    
return entityManager.createNativeQuery(sql, ProductSchema.class).setParameter("category",category).getResultList();
}

 

    
    
}
