package com.wow.server.controller;

import com.wow.server.dto.ErrorDTO;
import com.wow.server.exception.DataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ErrorDTO handleNotFoundError(DataNotFoundException exception) {
        return new ErrorDTO(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorDTO handleNotFoundError(Exception e) {
        log.error(e.getMessage(), e);
        return new ErrorDTO("Internal server error. Please contact system administrator.");
    }
}
