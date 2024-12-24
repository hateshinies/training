package com.greatestsasha.training.repository;

import com.greatestsasha.training.model.Comment;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CommentRepository extends R2dbcRepository<Comment, Long> {
}