package com.example.fakecom.ObjectMappers;

import java.util.Objects;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.fakecom.DTOs.RequestDTOs.CartOrderRequest;
import com.example.fakecom.ObjectMapperUtils.ProductCheck;
import com.example.fakecom.schema.OrderProduct;

 

 

@Mapper(componentModel="spring")
 
public abstract class OrderMapper {
 
    @Autowired
    private ProductCheck productCheck;
    
    
     @BeanMapping(nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.IGNORE,ignoreByDefault=true,qualifiedByName="intialcreation")
     @Mapping(source="quantity" ,target="quantity" )
    public  abstract OrderProduct createSchema(CartOrderRequest data);

    @InheritInverseConfiguration(name="createSchema")
    public abstract  CartOrderRequest createDTO(OrderProduct data);


    @AfterMapping
    @Named("intialcreation") // intianly when wer are creating the producycart
    public  void addingAddtionalDetails(CartOrderRequest dto,@MappingTarget OrderProduct data){

        data.setProduct(productCheck.addingProduct(dto));
        data.setOrder(productCheck.trackingId());
        
    }


    @BeanMapping(ignoreByDefault=true,nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE,qualifiedByName="updating")
    @Mapping(source="quantity" ,target="quantity")
    public abstract void  updateCart(CartOrderRequest dto,@MappingTarget OrderProduct data);
 // qantuty is already mapped onlt ledt the prodict od
 @AfterMapping
 @BeanMapping(ignoreByDefault=true,nullValuePropertyMappingStrategy=NullValuePropertyMappingStrategy.IGNORE)
 @Named("updating")
    public void  updateCartDetails(CartOrderRequest dto,@MappingTarget OrderProduct data){

        if(Objects.isNull(data)){

        }
        else{

            data.setProduct(productCheck.addingProduct(dto));
        }

    }


    

    




}
