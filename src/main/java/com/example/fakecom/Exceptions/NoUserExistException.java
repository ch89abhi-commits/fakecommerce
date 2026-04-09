package com.example.fakecom.Exceptions;

 

public class NoUserExistException  extends RuntimeException{
     
    public NoUserExistException(String msg){
        super(msg);      
    }
    
}
