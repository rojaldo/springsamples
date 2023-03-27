package com.example.springSamples.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ControllerAdvice 
class HandleExceptionsBeersController {

    @ExceptionHandler(Error404Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error404Handler(Error404Exception ex) {
        return "Ocurrió un error al procesar la solicitud: " + ex.getMessage();
    }

    @ExceptionHandler(Error409Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public String error409Handler(Error409Exception ex) {
        return "Ocurrió un error al procesar la solicitud: " + ex.getMessage();
    }
}

class Error404Exception extends RuntimeException {
    public Error404Exception(long id) {
        super("Beer not found with id: " + id);
    }
}
class Error409Exception extends RuntimeException {
    public Error409Exception(String name) {
        super("Beer already exists with name: " + name);
    }
}