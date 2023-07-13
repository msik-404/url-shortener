package com.msik404.urlshortener.url;

import lombok.Data;

@Data
public class Url {

    private String realUrl;
    private String shortPhrase;

    public Url(String realUrl, String shortPhrase) {
        this.realUrl = realUrl;
        this.shortPhrase = shortPhrase.replace("/", "-");
    }

}
