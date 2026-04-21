package com.example.fakecom.Responses;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.example.fakecom.Utility.ClientResponse;
import com.example.fakecom.controllers.OrderControllers.BasicCartControllerImpl;

@RestControllerAdvice(assignableTypes = BasicCartControllerImpl.class)

// what data i get to recieve from the contrller
// only able to write the body respnse field only the foramting in the body 
public class OrderResponse implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // You can also add a check here to ensure you don't wrap 
        // something that is already a ClientResponse
        return true; 
    }

    @Override
    public ClientResponse<Object> beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, 
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, 
                                  ServerHttpRequest request, ServerHttpResponse response) {
        
        // Prevent double-wrapping if the controller already returned ClientResponse
        // if (data instanceof ClientResponse) {
        //     return data;
        // }

        return ClientResponse.SuccessResponse(HttpStatus.ACCEPTED.value(), data);
    }
}