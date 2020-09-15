package com.serasa.parking.service.exception;

/**
 * Class Exception Object Not Found
 * @author vinicius.montouro
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String message){
        super(message);
    }
}
