package com.example.board.domain.user.application;

import com.example.board.domain.user.domain.User;
import com.example.board.domain.user.dto.UserRequest;
import com.example.board.domain.user.dto.UserResponse;
import com.example.board.domain.user.persistence.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.ListenerNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(UserRequest request) {
        // 1. 유저를 생성한다.
        User user = User.create(request);
        // 2. 유저를 저장한다.
        userRepository.save(user);

        // 3. return 시켜준다
        return new UserResponse(user);
    }

    public List<UserResponse> findAll() {
        List<User> user = userRepository.findAll();

        List<UserResponse> data = user.stream().map(UserResponse::new).collect(Collectors.toList());

        return data;
    }

    public UserResponse findOne(Long userId) {
        return new UserResponse(userRepository.findById(userId).get());
    }


    @Transactional
    public UserResponse update(UserRequest userRequest, Long userId) {

        User user = userRepository.findById(userId).orElseThrow();

        user.updateUser(userRequest);

        return new UserResponse(user);
    }


    @Transactional
    public UserResponse delete(Long userId) {

        User user = userRepository.findById(userId).get();

        userRepository.delete(user);

        return new UserResponse(user);
    }

}
