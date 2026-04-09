package com.example.fakecom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
// we are needed to overrid teh /erro page in order oo override
public class BasicErrorcontroller implements ErrorController {// this error contoller or abstract error controller is for the /error page
    @Autowired
    public ErrorAttributes errorAttributes;


    @RequestMapping("/error")
    public void invalidPath(HttpServletRequest ex) throws  NoResourceFoundException{


        // ServeletWebRequest req=new ServeletWebRequest(request);
   

    String method =ex.getMethod();
    String uri =(String) ex.getAttribute("jakarta.servlet.error.request_uri");

    throw new NoResourceFoundException(HttpMethod.valueOf(method), uri,uri);
    
    }



    
}