package com.greatestsasha.training.repository;

import com.greatestsasha.training.model.Post;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface PostRepository extends R2dbcRepository<Post, Long> {
}