package com.swapnilshah5889.Bookstore.models.response;

import org.springframework.http.ResponseEntity;

public class ApiResponse {
    boolean status;
    String message;
    Object response;
    
    public ApiResponse() {}

    public ApiResponse(boolean status, String message, Object response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }  
    
}
