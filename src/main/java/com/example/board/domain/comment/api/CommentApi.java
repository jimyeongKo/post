package com.example.board.domain.comment.api;

import com.example.board.domain.comment.application.CommentService;
import com.example.board.domain.comment.dto.CommentRequest;
import com.example.board.domain.comment.dto.CommentResponse;
import com.example.board.global.response.ApiListResponse;
import com.example.board.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("comment")
public class CommentApi {

    private final CommentService service;

    @PostMapping()
    public ApiResponse<CommentResponse> create(@RequestBody CommentRequest request) {
        return new ApiResponse<>(HttpStatus.CREATED, service.create(request));
    }

    @GetMapping("{parentId}")
    public ApiListResponse<CommentResponse> reCommentList(@PathVariable Long parentId) {
        return new ApiListResponse<>(HttpStatus.OK, service.findReComment(parentId));
    }

}
