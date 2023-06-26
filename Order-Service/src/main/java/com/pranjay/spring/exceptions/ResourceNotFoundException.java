package com.pranjay.spring.exceptions;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String s) {
        super(s);
    }

}
