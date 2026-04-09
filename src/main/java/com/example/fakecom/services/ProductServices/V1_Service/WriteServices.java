package com.example.fakecom.services.ProductServices.V1_Service;

import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;

import jakarta.transaction.Transactional;
 

 
public interface  WriteServices {
     
    public DetailProductResponse createProduct(DetailProductRequest data);
    
    
    @Transactional
    public DetailProductResponse updateProduct(Long id,DetailProductRequest data) throws Exception;


    // deleteing the priduct as a enrtitur
    public DetailProductResponse deleteProductEntity(Long id);
        

    
    
}
