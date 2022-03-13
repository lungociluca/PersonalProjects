package com.utcn.lab2.service;

import com.utcn.lab2.model.User;
import com.utcn.lab2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;

    public User findByID(Integer id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.orElse(null);
    }

    public User saveUser(User user) {
        return iUserRepository.save(user);
    }
}
