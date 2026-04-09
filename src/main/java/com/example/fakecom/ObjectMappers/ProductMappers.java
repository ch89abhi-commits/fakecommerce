package com.example.fakecom.ObjectMappers;



import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;
import com.example.fakecom.schema.ProductSchema;

// ensure that the their proper naming convencion so that hte external library should use some getter setters from the library
@Mapper(componentModel="spring")
 

public interface  ProductMappers {

// simple schema to DTO changing from the mappers

    @Mapping(source="name",target="productName")
    @Mapping(source="description",target="productDescription" )
    @Mapping(source="rating",target="productRating" )
    @Mapping(source="image",target="productImageDatabaseURL")
    @Mapping(source="price" ,target="productPrice")
    DetailProductRequest productToSchemDetailProductInput(ProductSchema data);






    @InheritInverseConfiguration(name="productToSchemDetailProductInput")
    // @Mapping(target="name",source="productName")
    // @Mapping(target="description",source="productDescription" , defaultValue="None")
    // @Mapping(target="rating",source="productRating" )
    // @Mapping(target="image",source="productImageDatabaseURL")
    // @Mapping(target="price" ,source="productPrice")
    ProductSchema productDtoToSchema(DetailProductRequest data);


    @Mapping(source="name",target="addedProductName")
    @Mapping(source="price",target="addedProductPrice")
    @Mapping(source="description",target="addedProductDescription" )
    @Mapping(source="rating",target="addedProductRating" )
    @Mapping(source="image",target="addedProductImageDatabaseURL")
 
    DetailProductResponse productSchemaToResponse(ProductSchema data);
    
    @InheritInverseConfiguration(name="productSchemaToResponse")
    ProductSchema productResponseToproductSchema(DetailProductResponse data);


    @BeanMapping(nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="name",source="productName")
    @Mapping(target="description",source="productDescription" , defaultValue="None")
    @Mapping(target="rating",source="productRating" )
    @Mapping(target="image",source="productImageDatabaseURL")
    @Mapping(target="price" ,source="productPrice")
    @Mapping(target="id",ignore=true)
    @Mapping(target="createdAt",ignore=true)
    @Mapping(target="updatedAt",ignore=true)
    @Mapping(target="deleteAt",ignore=true) /// need to add it inot the global congiguratoion because if any other entity is haveing that it should automaotically added up
    void ProductSchemaUpdateFromDTO(DetailProductRequest data, @MappingTarget ProductSchema enity );

     



}