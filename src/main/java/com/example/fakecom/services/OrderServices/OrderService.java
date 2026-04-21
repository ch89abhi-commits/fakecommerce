package com.example.fakecom.services.OrderServices;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fakecom.DTOs.RequestDTOs.CartOrderRequest;
import com.example.fakecom.ObjectMappers.OrderMapper;
import com.example.fakecom.repositories.OrderRepository;
import com.example.fakecom.schema.OrderProduct;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OrderService {
    public final OrderRepository orderRepository;
    private final OrderMapper orderMapper;



   // no need to make teh transaction as it is already one unit operation also none is required

    public List<OrderProduct> getAllCarts(){

        return orderRepository.findAll();

    }

    public OrderProduct getCardById(Long id){
        return orderRepository.findById(id).orElseThrow();
    }

    @Transactional
    public boolean deleteCartById(Long id){
        orderRepository.deleteById(id);
        return true;
    }


    public OrderProduct updateById(Long id, CartOrderRequest data){
        OrderProduct cart=orderRepository.findById(id).orElseThrow();
        orderMapper.updateCart(data, cart);
        // already do the dirty chaeck and update this data; // withou5 the transaction fo the this will not haapes
        orderRepository.save(cart);
        return cart;

    }

    public OrderProduct makeCart(CartOrderRequest data){
        
        return orderRepository.save(orderMapper.createSchema(data));

    }











}
