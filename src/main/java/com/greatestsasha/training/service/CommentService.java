package com.greatestsasha.training.service;

import com.greatestsasha.training.model.Comment;
import com.greatestsasha.training.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Flux<Comment> getAll() {
        return commentRepository.findAll();
    }

    public Mono<ResponseEntity<Comment>> getById(Long id) {
        return commentRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Void> saveAll(Publisher<Comment> commentFlux) {
        return commentRepository.saveAll(commentFlux).then();
    }
}