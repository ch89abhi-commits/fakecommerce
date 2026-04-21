package com.example.fakecom.controllers.OrderControllers.V1_Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fakecom.DTOs.RequestDTOs.CartOrderRequest;
import com.example.fakecom.schema.OrderProduct;

@RequestMapping("/api/v1/order")
public interface  OrderController {


    //dto
    @GetMapping("/allCarts")
    public List<OrderProduct> getAllCarts();

    
    @GetMapping("/{id}")
    public OrderProduct getCart(@PathVariable(value="id") Long id);

    @PostMapping("/create")
    public OrderProduct createCart(@RequestBody CartOrderRequest data);

    @DeleteMapping("/{id}/delete")
    public boolean  deleteCart(@PathVariable(value="id") Long id);


    @PatchMapping("/{id}/update")
    public OrderProduct updateCart(@PathVariable(name="id") Long id, @RequestBody CartOrderRequest data) ; // tsking teh request poarams of the dto;
    

    


    
}
