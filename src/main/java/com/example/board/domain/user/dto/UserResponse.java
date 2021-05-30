package com.example.board.domain.user.dto;

import com.example.board.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long userId;

    private String userName;

    private String name;

    private String password;

    private String phone;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.userName = user.getUserName();
        this.name = user.getName();
        this.password = user.getPassword();
        this.phone = user.getPhoneNum();
    }
}
