package com.msik404.urlshortener.url;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UrlOccupiedAdvice {

    @ResponseBody
    @ExceptionHandler(UrlTakenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String urlTakenHandler(UrlTakenException ex) {
        return ex.getMessage();
    }
}
