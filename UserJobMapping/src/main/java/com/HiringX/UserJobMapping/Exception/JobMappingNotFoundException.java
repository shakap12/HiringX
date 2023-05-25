package com.HiringX.UserJobMapping.Exception;

public class JobMappingNotFoundException extends RuntimeException{

    public JobMappingNotFoundException(Long id){
        super("Job Mappings for ID-"+id+" Not Found");
    }
}
