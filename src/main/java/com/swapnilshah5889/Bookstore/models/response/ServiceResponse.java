package com.swapnilshah5889.Bookstore.models.response;

public class ServiceResponse {
    private Object response;
    private String message;
    private Object error;
    private String errorMessage;
    private boolean isSuccess;
    
    public ServiceResponse() { }

    public ServiceResponse(Object response, Object error, String errorMessage) {
        this.response = response;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ApiResponse getErrorResponse() {
        return new ApiResponse(
            this.isSuccess(),
            this.getErrorMessage(),
            this.getError()
        );
    }

    public ApiResponse getSuccessResponse() {
        return new ApiResponse(
            this.isSuccess(),
            this.getMessage(),
            this.getResponse()
        );
    }
    
}
