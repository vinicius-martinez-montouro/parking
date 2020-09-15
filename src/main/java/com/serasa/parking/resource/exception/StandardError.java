package com.serasa.parking.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class to Error Exception
 * @author vinicius.montouro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError{

    private Long timeStamp;

    private Integer status;

    private String error;

    private String message;

    private String path;
}
