package com.greatestsasha.training.messaging;

import com.greatestsasha.training.handler.ChatWebSocketHandler;
import com.greatestsasha.training.model.messages.ChatMessage;
import com.greatestsasha.training.util.ObjectStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class RedisMessageListener {

    public static final String MESSAGE_TOPIC = "MESSAGE";

    private final ReactiveStringRedisTemplate reactiveStringRedisTemplate;
    private final ChatWebSocketHandler chatWebSocketHandler;
    private final ObjectStringConverter objectStringConverter;

    public RedisMessageListener(ReactiveStringRedisTemplate reactiveStringRedisTemplate,
                                    ChatWebSocketHandler chatWebSocketHandler,
                                    ObjectStringConverter objectStringConverter) {
        this.reactiveStringRedisTemplate = reactiveStringRedisTemplate;
        this.chatWebSocketHandler = chatWebSocketHandler;
        this.objectStringConverter = objectStringConverter;
    }

    public Mono<Void> subscribeMessageChannelAndPublishOnWebSocket() {
        return reactiveStringRedisTemplate.listenTo(new PatternTopic(MESSAGE_TOPIC))
                .map(ReactiveSubscription.Message::getMessage)
                .flatMap(message -> objectStringConverter.stringToObject(message, ChatMessage.class))
                .filter(chatMessage -> !chatMessage.getMessage().isEmpty())
                .flatMap(chatWebSocketHandler::sendMessage)
                .then();
    }
}