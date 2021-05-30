package com.example.board.domain.comment.dto;

import com.example.board.domain.comment.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long id;

    private String content;

    private String writer;

    private LocalDateTime createTime;

    private String boardTitle;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getCommentContent();
        this.writer = comment.getUser().getName();
        this.createTime = comment.getCommentDate();
        this.boardTitle = comment.getBoard().getTitle();
    }
}
