package com.fintech.demo.dto;


import lombok.Builder;
import lombok.Data;

@Data
//@Builder
public class ApiResponseJSON<T> {

    public ApiResponseJSON(String message) {
        this.message = message;
        this.payload = null;
        this.success=false;
    }
    public ApiResponseJSON(String message, T payload, boolean success) {
        this.message = message;
        this.payload = payload;
        this.success= success;
    }

    public ApiResponseJSON(String message, boolean success, Integer start, Integer limit, Long size, T payload) {
        this.message = message;
        this.success = success;
        this.start = start;
        this.limit = limit;
        this.size = size;
        this.payload = payload;
    }

    private String message;
    private boolean success;
    private Integer start;
    private Integer limit;
    private Long size;
    private T payload;

}
