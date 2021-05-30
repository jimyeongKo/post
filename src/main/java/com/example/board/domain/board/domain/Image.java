package com.example.board.domain.board.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Setter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public static Image create(String path) {
        Image image = Image.builder()
                .build();
        image.setPath(path.replace("HEIC", "jpg"));

        return image;
    }
}
