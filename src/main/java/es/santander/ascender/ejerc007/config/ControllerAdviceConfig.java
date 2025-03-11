package es.santander.ascender.ejerc007.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.santander.ascender.ejerc007.controller.MyBadDataException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerAdviceConfig {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MyBadDataException.class)
    @ResponseBody ErrorInfo handleBadRequest(HttpServletRequest req, Exception ex) {
        MyBadDataException myException = (MyBadDataException) ex;
        
        return new ErrorInfo(45, "No puedo acceder al registro " + myException.getRegistro());
    } 
}