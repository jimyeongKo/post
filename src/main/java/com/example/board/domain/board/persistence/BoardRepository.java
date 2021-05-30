package com.example.board.domain.board.persistence;

import com.example.board.domain.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
