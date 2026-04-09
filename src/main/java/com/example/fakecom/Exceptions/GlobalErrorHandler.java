package com.example.fakecom.Exceptions;



import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.fakecom.Utility.ClientResponse;



@RestControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler { // the response entity exception handler handler the other set of the methods
    

    @ExceptionHandler(value=NotImplemented.class)
    public ResponseEntity<ClientResponse<?>> ApiNotImplemented(NotImplemented ex){
         
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                .body(
                        ClientResponse.UnsupportedResponse("Not Yet Implemented")
                    );
           

    }


 

    

    @ExceptionHandler(value=NoUserExistException.class) 
    public  ResponseEntity< ClientResponse<?>> NoUserException(NoUserExistException ex){ // this the ? represent that the generic data can be null it must be present or not bale tp prenet
        return ResponseEntity
                .status(
                    HttpStatus.BAD_GATEWAY.value())
                .body(
                    ClientResponse.FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "no user exoist"));

    }



    @ExceptionHandler(value=Exception.class)
    public  ResponseEntity< ClientResponse<?>> generalException(Exception ex){
        return ResponseEntity
        .status(
            HttpStatus.BAD_GATEWAY.value())
        .body(
            ClientResponse.FailedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "unhandled exceptions"));


    }
    
    //  protected  ResponseEntity<ClientResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException ex,){  
        
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClientResponse.NoApiPathListed("Path Not Found"));
    // }
    
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(
        NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
           

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClientResponse.NoApiPathListed("Path Not Foundat "));
}
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(

        NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
           

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ClientResponse.NoApiPathListed("Path Not Found in"));
}




}
