package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.exceptions.UserException;
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
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @GetMapping("/getAll")
    public List<User> getUsers(){

        List<User> users = userRepository.findAll();
        return users;

    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Integer id) throws UserException{
        User user = userService.findUserById(id);
        return user;

    }

    @PutMapping("/update")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);
        User updatedUser = userService.updateUser(user, reqUser.getId());
        return updatedUser;
    }

    @PutMapping("/follow/{userId}")
    public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer userId) throws UserException {

        User reqUser = userService.findUserByJwt(jwt);
        User user = userService.followUser(reqUser.getId(), userId);
        return user;
    }

    @GetMapping("/search")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> users=userService.searchUser(query);
        return users;
    }


    @GetMapping("/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt){

        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;
    }

}
