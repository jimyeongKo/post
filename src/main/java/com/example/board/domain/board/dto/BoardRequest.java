package com.example.board.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BoardRequest {

    private String title;

    private String content;

    private Long userId;
}
