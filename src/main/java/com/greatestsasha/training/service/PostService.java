package com.greatestsasha.training.service;

import com.greatestsasha.training.model.Post;
import com.greatestsasha.training.model.dto.PostDto;
import com.greatestsasha.training.model.dto.ResponseDto;
import com.greatestsasha.training.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Flux<Post> getAll() {
        return postRepository.findAll();
    }

    public Mono<ResponseEntity<Post>> getById(Long id) {
        return postRepository.findById(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseDto<PostDto>> save(PostDto postFlux) {
        return postRepository.save(postFlux.createPost())
                .flatMap(post -> Mono.just(new ResponseDto<>(0, "", new PostDto(post))
                ));
    }
}