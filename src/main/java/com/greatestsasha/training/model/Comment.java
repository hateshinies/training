package com.greatestsasha.training.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {

    @Id
    private Long id;
//
//    @ManyToOne()
//    @JoinColumn(name = "post_id")
//    private Post post;
    private Long postId;
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Comment(Long postId, String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.postId = postId;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}