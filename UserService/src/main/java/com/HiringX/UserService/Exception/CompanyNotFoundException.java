package com.HiringX.UserService.Exception;

public class CompanyNotFoundException extends RuntimeException{

    public CompanyNotFoundException(String company){
        super("Given company-"+company+" Not Found");
    }
}
