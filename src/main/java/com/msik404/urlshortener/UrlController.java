package com.msik404.urlshortener;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/urls")
public class UrlController {

    @Autowired
    private ReactiveStringRedisTemplate template;

    @GetMapping("/{shortUrl}")
    public Mono<ResponseEntity<Void>> redirectToRealUrl(@PathVariable String shortUrl) {

        return template.opsForValue().get(shortUrl)
                .map(url -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(URI.create(url));
                    return new ResponseEntity<Void>(headers, HttpStatus.PERMANENT_REDIRECT);
                })
                .switchIfEmpty(Mono.error(new UrlNotFoundException(shortUrl)));
    }

    @PostMapping()
    public Mono<ResponseEntity<Url>> shortenUrl(@RequestBody Mono<Url> urlMono) {

        return urlMono.flatMap(url -> {
            Mono<Boolean> wasSetMono = template.opsForValue().setIfAbsent(url.getShortUrl(), url.getRealUrl());
            return wasSetMono.map(wasSet -> {
                if (!wasSet) {
                    throw new UrlTakenException(url.getShortUrl());
                }
                return ResponseEntity.ok(url);
            });
        });
    }
}
