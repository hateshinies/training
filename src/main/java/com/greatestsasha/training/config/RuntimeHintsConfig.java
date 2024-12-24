package com.greatestsasha.training.config;

import com.greatestsasha.training.model.messages.ChatMessage;
import com.greatestsasha.training.model.messages.Message;
import com.greatestsasha.training.model.messages.Platform;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.stereotype.Component;

@Component
@ImportRuntimeHints(RuntimeHintsConfig.SerdeRuntimeHints.class)
public class RuntimeHintsConfig  {

    static class SerdeRuntimeHints implements RuntimeHintsRegistrar {

        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            hints.serialization()
                    .registerType(ChatMessage.class)
                    .registerType(Message.class)
                    .registerType(Platform.class);
        }
    }
}