package com.envoltagroup.energy_management.configurations.exceptions;

import com.envoltagroup.energy_management.dtos.error.ErrorResponse;
import com.envoltagroup.energy_management.dtos.error.FieldErrors;
import com.envoltagroup.energy_management.exceptions.MachineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerConfiguration {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldErrors> fieldErrorsList = new ArrayList<>();

        exception.getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();

            FieldErrors fieldErrors = FieldErrors.builder()
                    .field(fieldName)
                    .errors(new ArrayList<>())
                    .build();
            fieldErrors.addError(errorMessage);
            
            fieldErrorsList.stream()
                    .filter(fieldErrorsOnList -> fieldErrorsOnList.equals(fieldErrors))
                    .findFirst()
                    .ifPresentOrElse(
                            fieldErrorsOnList -> fieldErrorsOnList.addError(errorMessage),
                            () -> fieldErrorsList.add(fieldErrors));
        });
        
        return ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusText(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .fieldErrors(fieldErrorsList)
                .build();
    }

    @ExceptionHandler(MachineNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleDefaultNotFound(MachineNotFoundException exception) {
        return ErrorResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .statusText(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }

    @ExceptionHandler({DateTimeParseException.class, HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDefaultBadRequest(Exception exception) {
        exception.printStackTrace();
        return ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusText("Invalid operation")
                .fieldErrors(Collections.emptyList())
                .build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleDefaultForbidden(BadCredentialsException exception) {
        return ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .statusText(exception.getMessage())
                .fieldErrors(Collections.emptyList())
                .build();
    }
}
