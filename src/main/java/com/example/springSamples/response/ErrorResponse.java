package com.example.springSamples.response;

public class ErrorResponse {
    public String message;
    public int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorResponse [message=" + message + ", status=" + status + "]";
    }
}
