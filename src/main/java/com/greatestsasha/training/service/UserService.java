package com.greatestsasha.training.service;

import com.greatestsasha.training.model.User;
import com.greatestsasha.training.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TransactionalOperator transactionalOperator;

    public Flux<User> createUserWithTransaction(User user) {
        // Handle the rollback scenario
//        userRepository.save(user).then().as(transactionalOperator::transactional);

        return transactionalOperator.execute(s ->
                userRepository.save(user)
                        .flatMap(savedUser -> {
                            // Simulate an error to trigger rollback
                            if (!user.getUsername().contains("@")) {
                                return Mono.error(new RuntimeException("Simulated error for rollback"));
                            }
                            return Mono.just(savedUser);
                        })
        ).onErrorResume(Mono::error);
    }
}