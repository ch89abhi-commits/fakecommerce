package com.example.fakecom.controllers.ProductControllers.V1_Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;
import com.example.fakecom.Utility.ClientResponse;

@RequestMapping("/api/v1/product")
public interface WriteProducts {


    // add products

    @PostMapping("/add")
    public ResponseEntity<ClientResponse<DetailProductResponse>>  addNewProduct(@RequestBody DetailProductRequest data);



    // update produts

    @PatchMapping("/{id}")
    public ResponseEntity<ClientResponse<DetailProductResponse>> updateProduct(@PathVariable(value="id") Long id , @RequestBody DetailProductRequest data);



    // delete products
 
     @DeleteMapping("/{id}")
    public ResponseEntity<ClientResponse<Object>> deleteProduct(@PathVariable(value="id") Long id);







      




    
    


}
