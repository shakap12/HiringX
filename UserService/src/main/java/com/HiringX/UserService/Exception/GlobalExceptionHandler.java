package com.HiringX.UserService.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundExceptionHandler(Exception e){
        log.warn("User Not Found Exception raised!");
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LocationNotFoundException.class)
    public String LocationNotFoundExceptionHandler(Exception e){
        log.warn("Location not found exception raised!");
        return e.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException.class)
    public String CompanyNotFoundExceptionHandler(Exception e){
        log.warn("Company not found exception raised!");
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String,String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.warn("Validation Exception for User Service!");
        HashMap<String,String> errorMap=new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(excep->{
            errorMap.put(excep.getField(),excep.getDefaultMessage());
        });
        return errorMap;
    }


}
