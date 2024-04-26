package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.exceptions.UserException;
import com.mynkjain.chatkaro.model.User;

import java.util.List;


public interface UserService {

    User registerUser(User user);

    User findUserById(Integer id) throws UserException;

    User findUserByEmail(String email);

    User followUser(Integer reqUserId, Integer followUserId) throws UserException;

    User updateUser(User user, Integer userId) throws UserException;
    List<User> searchUser(String query);

    public User findUserByJwt(String jwt);

}
