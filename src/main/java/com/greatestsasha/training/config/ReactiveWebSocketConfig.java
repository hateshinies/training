package com.greatestsasha.training.config;

import com.greatestsasha.training.handler.ChatWebSocketHandler;
import com.greatestsasha.training.messaging.RedisMessagePublisher;
import com.greatestsasha.training.model.messages.ChatMessage;
import com.greatestsasha.training.util.ObjectStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Sinks;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration(proxyBeanMethods = false)
public class ReactiveWebSocketConfig {

    public static final String WEBSOCKET_MESSAGE_MAPPING = "/redis-chat/**";

    @Bean
    public ChatWebSocketHandler webSocketHandler(RedisMessagePublisher redisChatMessagePublisher,
                                                 RedisAtomicLong activeUserCounter,
                                                 ObjectStringConverter objectStringConverter) {

        Sinks.Many<ChatMessage> chatMessageSink = Sinks.many().multicast().directBestEffort();
        return new ChatWebSocketHandler(chatMessageSink, redisChatMessagePublisher, activeUserCounter, objectStringConverter);
    }

    @Bean
    public HandlerMapping webSocketHandlerMapping(ChatWebSocketHandler webSocketHandler) {
        Map<String, WebSocketHandler> urlMap = new HashMap<>();
        urlMap.put(WEBSOCKET_MESSAGE_MAPPING, webSocketHandler);

        SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
        handlerMapping.setCorsConfigurations(Collections.singletonMap("*", new CorsConfiguration().applyPermitDefaultValues()));
        handlerMapping.setOrder(1);
        handlerMapping.setUrlMap(urlMap);
        return handlerMapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}