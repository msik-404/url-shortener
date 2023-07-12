package com.msik404.urlshortener;

public class UrlTakenException extends RuntimeException {
    UrlTakenException(String url) {
        super(String.format("url: %s is already taken", url));
    }
}
