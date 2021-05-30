package com.example.board.domain.user.domain;

import com.example.board.domain.user.dto.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    private String name;

    private String phoneNum;


    public static User create(UserRequest request) {
        return User.builder()
                .userName(request.getUserName())
                .name(request.getName())
                .password(request.getPassword())
                .phoneNum(request.getPhone())
                .build();
    }

    public void updateUser(UserRequest request) {
        this.userName = request.getUserName();
        this.password = request.getPassword();
        this.name = request.getName();
        this.phoneNum =request.getPhone();
    }
}
