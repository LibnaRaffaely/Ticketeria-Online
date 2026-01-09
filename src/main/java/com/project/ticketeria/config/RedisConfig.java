package com.project.ticketeria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory connectionFactory) {
        if (connectionFactory == null) {
            throw new IllegalArgumentException("RedisConnectionFactory cannot be null");
        }
        return new StringRedisTemplate(connectionFactory);
    }
}