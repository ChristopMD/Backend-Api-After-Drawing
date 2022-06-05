package com.afterdrawing.backendapi.service;
import com.afterdrawing.backendapi.core.entity.User;
import com.afterdrawing.backendapi.core.repository.UserRepository;
import com.afterdrawing.backendapi.exception.ResourceNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Admin"));
        return new
                org.springframework.security
                        .core.userdetails.User(user.getEmail(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
    }



}
