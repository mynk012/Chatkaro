package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.UserRepository;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @PostMapping("/createUser")
    public User createUser(@RequestBody User user){

        User savedUser=userService.registerUser(user);

        return savedUser;
    }

    @GetMapping("/api/getAll")
    public List<User> getUsers(){

        List<User> users = userRepository.findAll();
        return users;

    }

    @GetMapping("/users/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws Exception{
        User user = userService.findUserById(id);
        return user;

    }

    @PutMapping("/users/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Integer userId) throws Exception {

        User updatedUser = userService.updateUser(user,userId);
        return updatedUser;
    }

    @PutMapping("/users/follow/{userId1}/{userId2}")
    public User followUserHandler(@PathVariable Integer userId1, @PathVariable Integer userId2) throws Exception {

        User user = userService.followUser(userId1, userId2);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users=userService.searchUser(query);
        return users;
    }

}
