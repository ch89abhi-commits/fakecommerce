package com.example.fakecom.services.ProductServices.V1_Service;

import com.example.fakecom.DTOs.RequestDTOs.CompleteProductRequest;
import com.example.fakecom.DTOs.RequestDTOs.DetailProductRequest;
import com.example.fakecom.DTOs.ResponseDTOs.DetailProductResponse;

import jakarta.transaction.Transactional;
 

 
public interface  WriteServices {
     
    public DetailProductResponse createProduct(DetailProductRequest data);

   
    public String createCompleteProduct(CompleteProductRequest data); // here in the data i have the data category id in the long
    
    
    @Transactional // this  makes teh request 
    public DetailProductResponse updateProduct(Long id,DetailProductRequest data) throws Exception;


    // deleteing the priduct as a enrtitur
    public DetailProductResponse deleteProductEntity(Long id);
        

    
    
}
