package com.example.fakecom.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CartOrderRequest {
 

    public Long order_id; // this is only to track the order detatils onces the order is createed
 
    public Long product_id; //for the creating order only for the one single product

    public Integer quantity;
    
}
