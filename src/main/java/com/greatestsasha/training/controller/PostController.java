package com.greatestsasha.training.controller;

import com.greatestsasha.training.model.Post;
import com.greatestsasha.training.model.dto.PostDto;
import com.greatestsasha.training.model.dto.ResponseDto;
import com.greatestsasha.training.service.PostService;
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
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping
    public Flux<Post> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Post>> getById(@PathVariable(value = "id") Long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseDto<PostDto>> create(@RequestBody PostDto postDto) {
        return service.save(postDto);
    }
}