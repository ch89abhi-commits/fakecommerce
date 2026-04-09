package  com.example.fakecom.DTOs.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class DetailProductRequest{


    private String productDescription;

    private Float productRating;

    private Float productPrice;

    private String productImageDatabaseURL;

    private String productName;


}