package com.encrypt.user.service;

import com.encrypt.user.exception.UserNotFoundException;
import com.encrypt.user.repository.UserRepository;
import com.encrypt.user.config.PasswordUtils;
import com.encrypt.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordUtils passwordUtils;

    public User save(User user){
        user.setPassword(passwordUtils.encrypt(user.getPassword()));
        user.setConfirmPassword(passwordUtils.encrypt(user.getConfirmPassword()));
        return userRepository.save(user);

    }

    public User getUserByLastName(String userLastName){
        User user = userRepository.findByLastName(userLastName);
        if(Objects.isNull(user)){
            throw new UserNotFoundException("User Not Found", 404);
        }
        return user;
    }

}
