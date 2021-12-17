package com.sojicute.cradle.api.exception;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    private String message;

    private List<Violation> violations = new ArrayList<>();

    public Response() {}

    public Response(List<Violation> violations) {
        this.violations = violations;
    }

    public Response(String message) {
        this.message = message;
    }

}
