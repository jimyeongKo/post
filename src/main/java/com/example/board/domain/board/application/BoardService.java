package com.example.board.domain.board.application;

import com.example.board.domain.board.domain.Board;
import com.example.board.domain.board.domain.Image;
import com.example.board.domain.board.dto.BoardRequest;
import com.example.board.domain.board.dto.BoardResponse;
import com.example.board.domain.board.persistence.BoardRepository;
import com.example.board.domain.board.persistence.ImageRepository;
import com.example.board.domain.comment.domain.Comment;
import com.example.board.domain.comment.dto.CommentResponse;
import com.example.board.domain.comment.persistence.CommentRepository;
import com.example.board.domain.user.domain.User;
import com.example.board.domain.user.persistence.UserRepository;
import com.example.board.global.utils.ResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository repository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public BoardResponse createBoard(BoardRequest request, List<MultipartFile> images) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();

        Board board = Board.create(request, user);

        uploadImage(images, board);
        repository.save(board);

        return new BoardResponse(board);
    }

    public List<BoardResponse> findAll() {
        List<Board> boards = repository.findAll();

        return boards.stream().map(BoardResponse::new).collect(Collectors.toList());
    }

    public BoardResponse find(Long boardId) {
        Board boards = repository.findById(boardId).orElseThrow();

        return new BoardResponse(boards);
    }

    @Transactional
    public BoardResponse update(BoardRequest request, Long boardId, List<MultipartFile> images) {
        Board board = repository.findById(boardId).orElseThrow();

        board.update(request);

        deleteImage(board);
        imageSave(images, board);

        return new BoardResponse(board);
    }

    @Transactional
    public String delete(Long boardId) {
        Board board = repository.findById(boardId).orElseThrow();
        deleteImage(board);
        repository.delete(board);
        return "Y";
    }

    public List<CommentResponse> findComment(Long boardId) {
        List<Comment> comment =  commentRepository.findByBoardIdAndParentNull(boardId);

        return comment.stream().map(CommentResponse::new).collect(Collectors.toList());
    }


    public void imageSave(List<MultipartFile> images, Board board) {
        uploadImage(images, board);
    }

    private void uploadImage(List<MultipartFile> images, Board board) {
        for (MultipartFile image : images) {
            final String IMAGE_LOCATION = "/banner/free/image/" + board.getId() + "/" + image.getOriginalFilename();

            try {
                ResourceUtil.saveFile(image, IMAGE_LOCATION);
                Image boardImage = Image.create(IMAGE_LOCATION);
                board.addImage(boardImage);
            } catch (IllegalAccessError e) {
            }
        }
    }

    public void deleteImage(Board board) {
            List<Image> boardImage = imageRepository.findByBoardId(board.getId());
            board.deleteImage(boardImage);
        for (Image image : boardImage) ResourceUtil.deleteFile(image.getPath());
    }
}
