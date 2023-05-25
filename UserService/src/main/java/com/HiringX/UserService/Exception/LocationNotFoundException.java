package com.HiringX.UserService.Exception;

public class LocationNotFoundException extends RuntimeException {

    public LocationNotFoundException(String location){
        super("Given Location-"+location+" Not Found");
    }
}
