package com.swapnilshah5889.Bookstore.models.response;

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

    public ApiResponse setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getResponse() {
        return response;
    }

    public ApiResponse setResponse(Object response) {
        this.response = response;
        return this;
    }  

    public ApiResponse setSuccessResponse( String message, Object response) {
        this.setMessage(message);
        this.setResponse(response);
        this.setStatus(true);
        return this;
    }

    public ApiResponse setErrorResponse( String message, Object response) {
        this.setMessage(message);
        this.setResponse(response);
        this.setStatus(false);
        return this;
    }    
    
}
