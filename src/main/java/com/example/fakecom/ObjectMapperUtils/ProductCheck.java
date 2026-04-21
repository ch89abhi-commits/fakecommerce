package com.example.fakecom.ObjectMapperUtils;

 
import org.springframework.stereotype.Component;

import com.example.fakecom.DTOs.RequestDTOs.CartOrderRequest;
import com.example.fakecom.repositories.OrderTrack;
import com.example.fakecom.repositories.ProductRepository;
import com.example.fakecom.schema.Order;
 
import com.example.fakecom.schema.OrderStatus;
import com.example.fakecom.schema.ProductSchema;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

public class ProductCheck {

    private final ProductRepository productRepository;
    private final OrderTrack orderTrack;

 
 

 public  ProductSchema  addingProduct(CartOrderRequest dto){
      System.out.println(dto.product_id);
      System.out.println(dto.quantity);
        return productRepository.findById(dto.getProduct_id()).orElseThrow();

    }


public Order trackingId(){
     Order track = Order.builder().status(OrderStatus.PENDING).build();
     return orderTrack.save(track);
        
    }

  



    
}
