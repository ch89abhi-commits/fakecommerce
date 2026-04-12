package com.example.fakecom.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CompleteProductRequest {

    
    private String productDescription;

    private Float productRating;

    private Float productPrice;

    private String productImageDatabaseURL;

    private String productName;

    private Long productCategoryId;

    private String categoryName;

    
}
