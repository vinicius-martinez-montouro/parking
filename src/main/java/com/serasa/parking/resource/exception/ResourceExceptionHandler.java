package com.serasa.parking.resource.exception;

import com.serasa.parking.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Class to Handler Exception Resource
 * @author vinicius.montouro
 */
@ControllerAdvice
public class ResourceExceptionHandler {
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
                        "Object not found",e.getMessage(),request.getRequestURI()));
    }
}
