package com.sojicute.cradle.dto;


import lombok.Data;

import java.util.List;

@Data
public class ErrorResponse {

    private int status;
    private String exception;
    private String message;
    private List<FieldError> fieldErrorList;
}
