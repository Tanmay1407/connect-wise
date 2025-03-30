package com.pateltanmay.connectwise.helpers;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message){
        super(message);
    }

    public ResourceNotFound(){
        super("Resource Not Found");
    }

}
