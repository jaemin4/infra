package com.v01.event.interfaces.model.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RestError {
    private String error;
    private String message;

    public RestError(String error, String message) {
        this.error = error;
        this.message = message;
    }

}
