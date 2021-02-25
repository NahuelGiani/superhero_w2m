package com.w2m.superhero.enums;

import org.springframework.http.HttpStatus;

public enum StatusCode {
	SUPERHERO_SAVED_OK(2000, HttpStatus.OK.value(), "Superhero saved! "),
	SUPERHERO_UPDATED_OK(2001, HttpStatus.OK.value(), "Superhero updated! "),
	SUPERHERO_DELETED_OK(2002, HttpStatus.OK.value(), "Superhero deleted! "),
	SUPERHERO_WITHOUT_NAME(4000, HttpStatus.BAD_REQUEST.value(), "The name is null or empty."),
	SUPERHERO_DOESNT_EXIST(4001, HttpStatus.BAD_REQUEST.value(), "The superhero does not exist. Id: ");

    private int internCode;
    private int statusCode;
    private String description;

    StatusCode(final int internCode, final int statusCode, final String description) {
        this.internCode = internCode;
        this.statusCode = statusCode;
        this.description = description;
    }

    public int getInternCode() {
        return internCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }
}
