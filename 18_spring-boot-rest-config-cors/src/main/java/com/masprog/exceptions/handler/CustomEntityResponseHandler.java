package com.masprog.exceptions.handler;

import com.masprog.exceptions.ExceptionResponse;
import com.masprog.exceptions.RequiredObjectIsNullException;
import com.masprog.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

   @ExceptionHandler(Exception.class)
   public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest request) {
       ExceptionResponse response = new ExceptionResponse(
               new Date(),
               ex.getMessage(),
               request.getDescription(false));

       return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
   }

   @ExceptionHandler(ResourceNotFoundException.class)
   public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception ex, WebRequest request) {
       ExceptionResponse response = new ExceptionResponse(
               new Date(),
               ex.getMessage(),
               request.getDescription(false));

       return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
   }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
