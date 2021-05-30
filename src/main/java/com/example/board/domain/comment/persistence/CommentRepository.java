package com.example.board.domain.comment.persistence;

import com.example.board.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardIdAndParentNull(Long id);
    List<Comment> findByParent(Comment comment);
}
