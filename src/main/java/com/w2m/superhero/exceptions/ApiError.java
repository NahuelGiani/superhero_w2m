package com.w2m.superhero.exceptions;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.w2m.superhero.enums.StatusCode;

public class ApiError implements Serializable {

	private static final long serialVersionUID = -8246830036290745703L;
	private int statusCode;
    private String description;
    private String dateTime;
    private String path;

    public ApiError() {
        dateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(new Date());
    }

    public ApiError(StatusCode errorCode) {
        this();
        this.statusCode = errorCode.getStatusCode();
        this.description = errorCode.getDescription();
    }
    
    public ApiError(StatusCode errorCode, String args) {
        this();
        this.statusCode = errorCode.getStatusCode();
        this.description = errorCode.getDescription().concat(args);
    }
    
    public ApiError(int statusCode) {
        this();
        this.statusCode = statusCode;
    }
    
    public ApiError(int statusCode, String description) {
        this();
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
}
