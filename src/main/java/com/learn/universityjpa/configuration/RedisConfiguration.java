package com.learn.universityjpa.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@ComponentScan("com.learn.universityjpa")
@EnableRedisRepositories(basePackages = "com.learn.universityjpa.entity")
public class RedisConfiguration {
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        // JedisConnectionFactory jedisConFactory
        //      = new JedisConnectionFactory();
        //  jedisConFactory.setHostName("localhost");
        // jedisConFactory.setPort(6379);
        // return jedisConFactory;
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}