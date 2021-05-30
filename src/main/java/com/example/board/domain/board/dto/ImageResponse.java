package com.example.board.domain.board.dto;

import com.example.board.domain.board.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {

    private Long id;
    private String path;

    public ImageResponse (Image image) {
        this.id = image.getId();
        this.path = image.getPath();
    }
}
