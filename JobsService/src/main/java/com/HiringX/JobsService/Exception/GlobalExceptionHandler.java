package com.HiringX.JobsService.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JobNotFoundException.class)
    public String JobNotFoundExceptionHandler(JobNotFoundException e){
        log.warn("Job Not Found Exception Raised");
        return (e.getMessage());
    }

    public HashMap<String,String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.warn("Validation Exception for Job Service");
        HashMap<String,String> errorMap=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(excep->{
            errorMap.put(excep.getField(), excep.getDefaultMessage());
        });
        return errorMap;
    }
}
