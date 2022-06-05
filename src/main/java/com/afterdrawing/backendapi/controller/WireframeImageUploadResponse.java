package com.afterdrawing.backendapi.controller;

public class WireframeImageUploadResponse {

    private String message;

    public WireframeImageUploadResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
