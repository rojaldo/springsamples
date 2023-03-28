package com.example.springSamples.response;

public class CustomerErrorResponse implements ICustomerResponse{
    public String message;
    public int status;

    public CustomerErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", status=" + status + "]";
    }
}
