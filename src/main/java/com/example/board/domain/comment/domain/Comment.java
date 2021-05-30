package com.example.board.domain.comment.domain;

import com.example.board.domain.board.domain.Board;
import com.example.board.domain.comment.dto.CommentRequest;
import com.example.board.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String commentContent;



    public static Comment create(CommentRequest request, Board board, User user, Comment parent) {
        return Comment.builder()
                .commentDate(LocalDateTime.now())
                .user(user)
                .parent(parent)
                .board(board)
                .commentContent(request.getCommentContent())
                .build();
    }

    public void update(CommentRequest dto) {
        this.commentContent = dto.getCommentContent();
    }
}
