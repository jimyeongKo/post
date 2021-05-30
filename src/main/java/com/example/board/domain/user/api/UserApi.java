package com.example.board.domain.user.api;

import com.example.board.domain.user.application.UserService;
import com.example.board.domain.user.dto.UserRequest;
import com.example.board.domain.user.dto.UserResponse;
import com.example.board.global.response.ApiListResponse;
import com.example.board.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserApi {

    private final UserService service;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody UserRequest request) {
        return new ApiResponse<>(HttpStatus.OK, service.createUser(request));
    }

    @GetMapping("/all")
    public ApiListResponse<UserResponse> findAll() {
        return new ApiListResponse<>(HttpStatus.OK, service.findAll());
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> findOne(@PathVariable Long userId) {
        return new ApiResponse<>(HttpStatus.OK, service.findOne(userId));
    }


    @PatchMapping("{userId}/update")
    public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserRequest request, @PathVariable Long userId) {
        return new ApiResponse<>(HttpStatus.OK, service.update(request, userId));
    }

    @DeleteMapping("{userId}/delete")
    public ApiResponse<UserResponse> deleteUser(@PathVariable Long userId) {
        return new ApiResponse<>(HttpStatus.OK, service.delete(userId));
    }
}
