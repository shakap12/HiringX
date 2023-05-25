package com.HiringX.UserJobMapping.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JobMappingNotFoundGlobalController {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JobMappingNotFoundException.class)
    public String JobMappingNotFoundExceptionHandler(Exception e){
        return e.getMessage();
    }
}
