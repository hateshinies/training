package com.greatestsasha.training.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class Post {

    @Id
    private Long id;

    //    @OneToMany(
//            mappedBy = "post",
//            fetch = FetchType.LAZY,
//            cascade = CascadeType.ALL
//    )
//    private Set<Comment> comments = new HashSet<>();
    private String text;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Post(String text, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.text = text;
//        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//    public void addComments(Comment comment) {
//        comment.setPost(this);
//        comments.add(comment);
//    }
}