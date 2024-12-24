package com.greatestsasha.training.controller;

import com.greatestsasha.training.model.Comment;
import com.greatestsasha.training.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @GetMapping
    public Flux<Comment> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Comment>> getById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Void> create(@RequestBody Publisher<Comment> commentFlux) {
        return service.saveAll(commentFlux);
    }
}