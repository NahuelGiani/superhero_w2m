package com.w2m.superhero.exceptions;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 8989407896706533948L;
	private final ApiError apiError;

    public ApiException(final ApiError apiError) {
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
