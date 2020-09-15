package com.serasa.parking.service.exception;

/**
 * Class Exception Object Duplicate
 * @author vinicius.montouro
 */
public class ObjectDuplicateException extends RuntimeException {
    public ObjectDuplicateException(String message){
        super(message);
    }
}
