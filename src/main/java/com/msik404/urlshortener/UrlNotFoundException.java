package com.msik404.urlshortener;

public class UrlNotFoundException extends RuntimeException {
    UrlNotFoundException(String url) {
        super(String.format("Could not find url: %s", url));
    }
}
