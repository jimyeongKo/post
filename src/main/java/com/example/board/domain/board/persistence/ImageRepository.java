package com.example.board.domain.board.persistence;

import com.example.board.domain.board.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByBoardId(Long boardId);
}
