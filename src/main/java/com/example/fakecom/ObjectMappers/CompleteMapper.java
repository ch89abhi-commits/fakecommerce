package com.example.fakecom.ObjectMappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.fakecom.DTOs.RequestDTOs.CompleteProductRequest;
import com.example.fakecom.ObjectMapperUtils.CategoryCheck;
import com.example.fakecom.repositories.CategoryRepository;
import com.example.fakecom.schema.CategorySchema;
import com.example.fakecom.schema.ProductSchema;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Mapper(componentModel="spring")
 
public abstract class CompleteMapper {
    
    @Autowired
    private   CategoryRepository categoryRepository;  // added this reposotory bean is to initailsse teh category schema..
    @Autowired
    private CategoryCheck categoryCheck;

    @Autowired
    @PersistenceContext
    private   EntityManager entityManager; // no need to have any of teh entity manger or any other 
    // teh entity manger is the opening the persistant session then starts at transaction and attacht othe persistancs session and start the 
    // lifecycle of teh database

    @BeanMapping(ignoreByDefault=true)
    @Mapping(source="productDescription" ,target="description")
    @Mapping(source="productRating" ,target="rating")
    @Mapping(source="productPrice" ,target="price")
    @Mapping(source="productImageDatabaseURL" ,target="image")
    @Mapping(source="productName" ,target="name")
    public  abstract ProductSchema ProductDTO (CompleteProductRequest data);
    
    // important thing ist he teh target category us the object of another class
    
    
    @AfterMapping
    public void addingCategory(CompleteProductRequest dto, @MappingTarget ProductSchema data){
        // this logic is clutter as here i want a seprate class for handling the data base logic 
        // it is the mapper class it only hold the mapping logic not the database;
        
        System.out.println("Is transaction active above? " + org.springframework.transaction.support.TransactionSynchronizationManager.isActualTransactionActive());

      
        //  CategorySchema ct=entityManager.find(CategorySchema.class, dto.getProductCategoryId()); // iintialised the xtl created teh object;
         
        //  if(ct==null){
        //     System.out.println("enterred");
        //     ct=CategorySchema.builder().name(dto.getCategoryName()).build();
        //     entityManager.persist(ct);

             
        //  }

        // CategorySchema ct=categoryRepository.findById(dto.getProductCategoryId()).orElseGet(
        //                                                              () -> {
        //                                                                 CategorySchema c =  CategorySchema.builder().name(dto.getCategoryName()).build();
        //                                                                 return categoryRepository.save(c);
        //                                                                 // return c;
        //                                                              }
        // );

        data.setCategory(categoryCheck.checkOrReturnCategory(dto));





        //  data.setCategory(ct);
 
    }

    




     
}
