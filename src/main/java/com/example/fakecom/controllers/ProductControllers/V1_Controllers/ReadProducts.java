package com.example.fakecom.controllers.ProductControllers.V1_Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fakecom.Utility.ClientResponse;
import com.example.fakecom.schema.ProductSchema;

@RequestMapping("/api/v1/product")
public interface ReadProducts {


    @GetMapping("/all")
    public ResponseEntity<ClientResponse<List<ProductSchema>>> getAllProducts(); 

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse<ProductSchema>> getById(@PathVariable(value="id") Long id);

    @GetMapping("/search")
    public ResponseEntity<ClientResponse<Object>> searchByCategory(@RequestParam String category ) throws Exception; //error

    @GetMapping("/categories")
    public ResponseEntity<ClientResponse<Object>> categories();
    
   

}
