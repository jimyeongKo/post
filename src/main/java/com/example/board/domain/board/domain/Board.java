package com.example.board.domain.board.domain;

import com.example.board.domain.board.dto.BoardRequest;
import com.example.board.domain.comment.domain.Comment;
import com.example.board.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.NegativeOrZero;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime createTime;

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private final List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "board", orphanRemoval = true, cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    public static Board create(BoardRequest request, User user) {
        return Board.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .createTime(LocalDateTime.now())
                .build();
    }

    public void addImage(Image image) {
        this.images.add(image);
        image.setBoard(this);
    }

    public void update(BoardRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void deleteImage(List<Image> image) {
        images.remove(image);
    }

}
