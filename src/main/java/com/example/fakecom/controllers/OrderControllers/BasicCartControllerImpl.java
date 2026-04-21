package com.example.fakecom.controllers.OrderControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.fakecom.DTOs.RequestDTOs.CartOrderRequest;
import com.example.fakecom.controllers.OrderControllers.V1_Controller.OrderController;
import com.example.fakecom.services.OrderServices.OrderService;
import com.example.fakecom.schema.OrderProduct;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class BasicCartControllerImpl implements  OrderController{

    private final OrderService orderService;

    @Override
    public List<OrderProduct> getAllCarts(){

        return orderService.getAllCarts();
        

    }

    
    @Override
    public OrderProduct getCart( Long id){
        return orderService.getCardById(id);
    }

     
    @Override
    public boolean  deleteCart(  Long id){
        return orderService.deleteCartById(id);
    }


    @Override
    public OrderProduct updateCart( Long id,  CartOrderRequest data) {
        return orderService.updateById(id, data);
    } // tsking teh request poarams of the dto;
    

    @Override
    public OrderProduct createCart(CartOrderRequest data){
        return  orderService.makeCart(data);
        }


    
}
