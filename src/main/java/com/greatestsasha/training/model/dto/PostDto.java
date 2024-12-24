package com.greatestsasha.training.model.dto;

import com.greatestsasha.training.model.Comment;
import com.greatestsasha.training.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private String text;
    private Set<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostDto(Post post) {
        this.id = post.getId();
        this.text = post.getText();
//        this.comments = post.getComments();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

    public Post createPost() {
        return Post.builder()
                .id(this.id)
                .text(this.text)
//                .comments(this.comments)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}