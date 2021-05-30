package com.example.board.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {

    private String commentContent;

    private Long userId;

    private Long boardId;

    private Long parent;
}
