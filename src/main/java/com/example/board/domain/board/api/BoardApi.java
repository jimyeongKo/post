package com.example.board.domain.board.api;

import com.example.board.domain.board.application.BoardService;
import com.example.board.domain.board.dto.BoardRequest;
import com.example.board.domain.board.dto.BoardResponse;
import com.example.board.domain.comment.dto.CommentResponse;
import com.example.board.global.response.ApiListResponse;
import com.example.board.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardApi {

    private final BoardService service;

    @PostMapping()
    public ApiResponse<BoardResponse> create(
            @RequestPart BoardRequest request,
            @RequestPart List<MultipartFile> image)
    {
        return new ApiResponse<>(HttpStatus.CREATED, service.createBoard(request, image));
    }

    @GetMapping("all")
    public ApiListResponse<BoardResponse> findAll() {
        return new ApiListResponse<>(HttpStatus.OK, service.findAll());
    }

    @GetMapping("/{boardId}/one")
    public ApiResponse<BoardResponse> findOne(@Valid @PathVariable Long boardId) {
        return new ApiResponse<>(HttpStatus.OK, service.find(boardId));
    }

    @PatchMapping("/{boardId}/update")
    public ApiResponse<BoardResponse> updateBoard(
            @PathVariable Long boardId, @RequestPart BoardRequest request,
            @RequestPart List<MultipartFile> image)
    {
        return new ApiResponse<>(HttpStatus.OK, service.update(request, boardId, image));
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<String> delete(
            @PathVariable Long boardId
    ) {
        return new ApiResponse<>(HttpStatus.OK, service.delete(boardId));
    }

    @GetMapping("/{boardId}/comment")
    public ApiListResponse<CommentResponse> findComment(@PathVariable Long boardId) {
        return new ApiListResponse<>(HttpStatus.OK, service.findComment(boardId));
    }
}
