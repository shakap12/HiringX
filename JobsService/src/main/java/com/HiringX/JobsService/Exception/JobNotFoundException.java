package com.HiringX.JobsService.Exception;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(Long id){
        super("Job with id-"+id+" not found");
    }
    public JobNotFoundException(String parameter,String value){
        super("Job Id's for "+parameter+"-"+value+" not found\n Please Enter correct "+parameter);
    }
    public JobNotFoundException(String parameter){
        super("Jobs for given-"+parameter+" not found\n Please enter correct value");
    }
}
