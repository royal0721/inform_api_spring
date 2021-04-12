package com.animal.api.Exception;

import com.animal.api.Exception.InformNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InformNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(InformNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(InformNotFoundException ex) {
        return ex.getMessage();
    }
}
