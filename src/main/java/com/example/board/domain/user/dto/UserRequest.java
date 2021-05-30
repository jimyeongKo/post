package com.example.board.domain.user.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String userName;
    private String name;
    private String phone;
    private String password;
}
