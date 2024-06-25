package com.furkanceylan.secondhand.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.InvalidClassException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,Object> body = new LinkedHashMap<>();
        body.put("timestamp",new Date());
        body.put("status", status.value());

        //Get ALL  Errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors" , errors);
        return  new ResponseEntity<>(body,headers,status);
    }


   /* @ExceptionHandler(ChargingS)
    public ResponseEntity<?> customerNotFoundExceptionHandler(InvalidClassException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidClassException.class)
    public ResponseEntity<?> customerNotFoundExceptionHandler(InvalidClassException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidClassException.class)
    public ResponseEntity<?> customerNotFoundExceptionHandler(InvalidClassException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    } */
   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<?> customerNotFoundExceptionHandler(UserNotFoundException exception)  {
       return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
   }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalExceptionHandler(Exception exception)  {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }












































}
