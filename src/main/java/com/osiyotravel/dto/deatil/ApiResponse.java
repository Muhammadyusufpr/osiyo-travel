package com.osiyotravel.dto.deatil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private String message;

    private Integer code;

    private Boolean isError;

    private T date;

    public ApiResponse() {
    }

    public ApiResponse(String message, Integer code, Boolean isError) {
        this.message = message;
        this.code = code;
        this.isError = isError;
        this.date = null;
    }

    public ApiResponse(Boolean isError, T date) {
        this.isError = isError;
        this.date = date;
    }

    public ApiResponse(Boolean isError) {
        this.isError = isError;
    }

    public ApiResponse(String message, Integer code, Boolean isError, T date) {
        this.message = message;
        this.code = code;
        this.isError = isError;
        this.date = date;
    }
}
