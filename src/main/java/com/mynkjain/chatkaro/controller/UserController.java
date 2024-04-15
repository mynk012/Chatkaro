package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);

        return savedUser;
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        List<User> users= new ArrayList<>();
        User user1 = new User(1,"code","myk","code@gamil.com","12345");
        users.add(user1);
        return users;
    }
}
