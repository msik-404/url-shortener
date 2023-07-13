package com.msik404.urlshortener.url;

public class UrlNotFoundException extends RuntimeException {
    UrlNotFoundException(String url) {
        super(String.format("Could not find url: %s", url));
    }
}
