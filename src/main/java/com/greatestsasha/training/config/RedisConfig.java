package com.greatestsasha.training.config;

import com.greatestsasha.training.messaging.RedisMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

@Slf4j
@Configuration(proxyBeanMethods = false)
//@Profile("!heroku")
public class RedisConfig {

    public static final String MESSAGE_COUNTER_KEY = "TOTAL_MESSAGE_COUNT";
    public static final String ACTIVE_USER_KEY = "ACTIVE_USER_COUNT";

    @Bean
    @Primary
    ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(RedisProperties redisProperties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration =
                new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    ReactiveStringRedisTemplate reactiveStringRedisTemplate(ReactiveRedisConnectionFactory reactiveRedisConnectionFactory) {
        return new ReactiveStringRedisTemplate(reactiveRedisConnectionFactory);
    }

    // Redis Atomic Counter to store no. of total messages sent from multiple app instances.
    @Bean
    RedisAtomicInteger chatMessageCounter(RedisConnectionFactory redisConnectionFactory) {
        return new RedisAtomicInteger(MESSAGE_COUNTER_KEY, redisConnectionFactory);
    }

    // Redis Atomic Counter to store no. of Active Users.
    @Bean
    RedisAtomicLong activeUserCounter(RedisConnectionFactory redisConnectionFactory) {
        return new RedisAtomicLong(ACTIVE_USER_KEY, redisConnectionFactory);
    }

    @Bean
    ApplicationRunner applicationRunner(RedisMessageListener redisChatMessageListener) {
        return args -> {
            redisChatMessageListener.subscribeMessageChannelAndPublishOnWebSocket()
                    .doOnSubscribe(subscription -> log.info("Redis Listener Started"))
                    .doOnError(throwable -> log.error("Error listening to Redis topic.", throwable))
                    .doFinally(signalType -> log.info("Stopped Listener. Signal Type: {}", signalType))
                    .subscribe();
        };
    }
}