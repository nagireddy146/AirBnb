package com.airBnb_application.firstproject.Advice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {

    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse() {
        this.timeStamp=LocalDateTime.now();
    }

    public ApiResponse( ApiError error) {
        this();
        this.error = error;
    }
    public ApiResponse(T data){
    this();
    this.data=data; }

}
