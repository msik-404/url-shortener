package com.msik404.urlshortener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {

    private final RedisProperties redisProperties;

    @Bean
    public ReactiveRedisConnectionFactory lettuceConnectionFactory() {

        String hostname = redisProperties.getHostname();
        int port = redisProperties.getPort();
        return new LettuceConnectionFactory(hostname, port);
    }

    @Bean
    ReactiveStringRedisTemplate template(ReactiveRedisConnectionFactory factory) {
        return new ReactiveStringRedisTemplate(factory, RedisSerializationContext.string());
    }

}
