package com.example.fakecom.controllers.CategoryControllers.V1_Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fakecom.DTOs.RequestDTOs.CategoryRequest;
import com.example.fakecom.Utility.ClientResponse;

@RequestMapping("/api/v1/category")
public interface WriteCategory {
    
      @PostMapping("/add")
    public ResponseEntity<ClientResponse<Object>> addingCategory(@RequestBody CategoryRequest data);

    
}
