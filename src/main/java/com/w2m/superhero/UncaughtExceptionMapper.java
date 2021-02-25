package com.w2m.superhero;

import java.util.Stack;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.w2m.superhero.exceptions.ApiError;
import com.w2m.superhero.exceptions.ApiException;

public class UncaughtExceptionMapper implements ExceptionMapper<Throwable> {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(UncaughtExceptionMapper.class);

    @Override
    public Response toResponse(Throwable cause) {
        final ApiError error;
        
        if (cause instanceof ApiException) {
            error = ((ApiException) cause).getApiError();       
            if(StringUtils.isEmpty(error.getDescription())) {
            	error.setDescription(getThrowableCause(cause));
            }
            LOGGER.error("ApiException. Error: {}", error);
            Response.Status statusCode = Response.Status.fromStatusCode(error.getStatusCode());
            if (statusCode == null) {
                statusCode = Response.Status.INTERNAL_SERVER_ERROR;
            }
            return Response.status(statusCode)
            		.type(MediaType.APPLICATION_JSON_TYPE)
            		.entity(error.toString()).build();
        } else {
            int responseStatusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
            if (cause instanceof NotFoundException) {
                responseStatusCode = Response.Status.NOT_FOUND.getStatusCode();
            }
            error = new ApiError(responseStatusCode);
            error.setDescription(getThrowableCause(cause));
            LOGGER.error("Uncaught exception. Error: {}", error, cause);
            return Response.status(responseStatusCode)
            		.type(MediaType.APPLICATION_JSON_TYPE)
            		.entity(error.toString()).build();
        }
    }

    public static String getThrowableCause(Throwable exception) {
        final Stack<Throwable> exceptionStack = new Stack<>();
        while (exception != null && !exceptionStack.contains(exception)) {
            exceptionStack.push(exception);
            exception = exception.getCause();
        }

        final StringBuilder errorDescription = new StringBuilder();
        while (!exceptionStack.isEmpty()) {
            final Throwable cause = exceptionStack.pop();
            if (errorDescription.length() > 0) {
                errorDescription.append(" -- ");
            }
            errorDescription.append(cause.getLocalizedMessage());
        }
        return errorDescription.toString();
    }
}
