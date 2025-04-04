package com.natixis.ecommerce.dto.response;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final long timestamp;
    private String path; // Optional: request path

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public long getTimestamp() { return timestamp; }
    public String getPath() { return path; }

    // Setters if needed
    public void setPath(String path) { this.path = path; }
}