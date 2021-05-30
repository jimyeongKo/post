package com.example.board.domain.comment.application;

import com.example.board.domain.board.domain.Board;
import com.example.board.domain.board.persistence.BoardRepository;
import com.example.board.domain.comment.domain.Comment;
import com.example.board.domain.comment.dto.CommentRequest;
import com.example.board.domain.comment.dto.CommentResponse;
import com.example.board.domain.comment.persistence.CommentRepository;
import com.example.board.domain.user.domain.User;
import com.example.board.domain.user.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository repository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 댓글 달기
    public CommentResponse create(CommentRequest request) {
        // 댓글 단 사람 가져오기
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        //어떤 개시글에 댓글인지 가져오기
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow();

        // 댓글 달기
        Comment comment = Comment.create(
                request,
                board,
                user,
                request.getParent() != null ? repository.findById(request.getParent()).orElseThrow() : null);

        // 댓글 영속화 시켜주기(저장 시켜주기)
        repository.save(comment);

        //return
        return new CommentResponse(comment);
    }

    // 재 댓글 가져오기
    public List<CommentResponse> findReComment(Long parentId) {
        Comment comment = repository.findById(parentId).orElseThrow();
        List<Comment> reComment = repository.findByParent(comment);

        return reComment.stream().map(CommentResponse::new).collect(Collectors.toList());
    }
}
