package com.example.fakecom.controllers.CategoryControllers.V1_Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.Utility.ClientResponse;
import com.example.fakecom.schema.CategorySchema;

 
@RestController
@RequestMapping("/api/v1/category")
public interface ReadContoller {


      @GetMapping("/{id}")         
    public CategoryRequest getCategory(@PathVariable(value="id") Long id);


    @GetMapping("/all")
    public ResponseEntity<ClientResponse<List<CategorySchema>>>  getAll();








}
