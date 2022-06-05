package com.afterdrawing.backendapi.service;

import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.core.service.UserService;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {




    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Email not found","email",email));
    }


    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        user.setUserName(userRequest.getUserName());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        //user.setEmail(userRequest.getEmail());
        //user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {

        return userRepository.save(user);


    }

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }


}
