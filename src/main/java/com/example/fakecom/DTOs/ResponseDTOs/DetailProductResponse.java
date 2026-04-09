package com.example.fakecom.DTOs.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DetailProductResponse {
    
    

    private String addedProductName;

    private Float addedProductRating;

    private Float addedProductPrice;

    private String addedProductImageDatabaseURL;

    private String addedProductDescription;

}
