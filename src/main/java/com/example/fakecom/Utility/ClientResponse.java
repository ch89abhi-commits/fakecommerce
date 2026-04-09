package com.example.fakecom.Utility;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
// this class add thebody to the client bodyu in the http header but the data that the body contatins should have the object ta
// are to be in the differnet fields


@AllArgsConstructor
@Data
@Builder


public class  ClientResponse <T> { // it defines that it take a generic datatypes that is define at the run time

    // here     we define the data that need to be sent to the client as the response for any of the particulare response
    
    private final boolean success;
    private final int code;
    private  final T data;
    private final String error;

    public static <T> ClientResponse<T> SuccessResponse(int code, T data){
            // define that the it is generaic method <T> and the return type of the function is depend upon the T data at run time

        return ClientResponse.<T>builder() // this defines the builder that the builder can use the generic objects
                .success(true)
                .code(code)
                .data(data)
                .error(null)
                .build();


    }


    public static <T> ClientResponse<T> FailedResponse(int code,String msg ){

        return ClientResponse.<T>builder()
                .code(code)
                .success(false)
                .error(msg)
                .build();

    }

    public static <T> ClientResponse<T> UnsupportedResponse(String msg){

        return ClientResponse.<T>builder()
                    .success(true)
                    .code(HttpStatus.NOT_IMPLEMENTED.value())
                    .build();
                    
    }


    public static <T> ClientResponse<T> NoApiPathListed(String msg){

        return ClientResponse.<T>builder()
                            .success(false)
                            .code(HttpStatus.NOT_FOUND.value())
                            .error(msg)
                            .build();
    }





    
}
