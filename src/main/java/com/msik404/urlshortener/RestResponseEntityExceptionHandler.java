package com.msik404.urlshortener;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import com.msik404.urlshortener.url.UrlNotFoundException;
import com.msik404.urlshortener.url.UrlTakenException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ResponseBody
    @ExceptionHandler(UrlTakenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlTakenHandler(UrlTakenException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UrlNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlNotFoundHandler(UrlNotFoundException ex) {
        return ex.getMessage();
    }
}
