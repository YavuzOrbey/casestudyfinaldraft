package com.casestudydraft.service;


import com.casestudydraft.model.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void save(User user);
    List<User> findAll();
    User findByEmail(String email);
    User findByUsername(String username);
}
