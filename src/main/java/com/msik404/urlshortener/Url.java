package com.msik404.urlshortener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Url {

    private String realUrl;
    private String shortUrl;
}
