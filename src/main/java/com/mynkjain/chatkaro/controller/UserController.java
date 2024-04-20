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

    @PutMapping("/users")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/users/follow/{userId}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId);
        return user;
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users=userService.searchUser(query);
        return users;
    }


    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

}
