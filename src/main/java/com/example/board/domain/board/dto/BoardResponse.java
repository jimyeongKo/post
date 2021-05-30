package com.example.board.domain.board.dto;

import com.example.board.domain.board.domain.Board;
import com.example.board.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardResponse {

    private Long id;

    private String userName;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private List<ImageResponse> images;


    public BoardResponse(Board board) {
        this.id = board.getId();
        this.userName = board.getUser().getUserName();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createTime = board.getCreateTime();
        this.images = board.getImages().stream().map(ImageResponse::new).collect(Collectors.toList());
    }
}
