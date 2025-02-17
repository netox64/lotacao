package com.oficinadobrito.api.configs.exeptions;

public class ResponseError {

    @SuppressWarnings("unused")
    private String reason;
    @SuppressWarnings("unused")
    private String description;

    public ResponseError() {
    }

    public ResponseError(String reason) {
        this.reason = reason;
    }

    public ResponseError(String reason, String description) {
        this.reason = reason;
        this.description = description;
    }

}
