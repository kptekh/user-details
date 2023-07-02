package com.encrypt.user.exception;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Data
public class ApiError implements Serializable {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

   /* @JsonIgnore
    private int code;
    private String field;
    private String value;
    private String message;

    public ApiErrors(String message) {
        this.message = message;
    }

    public ApiErrors(String field, String value, String message) {
        this.field = field;
        this.value = value;
        this.message = message;
    }*/
}
