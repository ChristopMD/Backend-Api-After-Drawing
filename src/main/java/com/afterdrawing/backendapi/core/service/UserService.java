package com.afterdrawing.backendapi.core.service;

import com.afterdrawing.backendapi.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.Optional;

public interface UserService {
    Page<User> getAllUsers(Pageable pageable);

    User getUserById(Long userId);

    User getUserByEmail(String email);


    User updateUser(Long userId, User userRequest);

    User saveUser(User user);

    ResponseEntity<?> deleteUser(Long userId);


}