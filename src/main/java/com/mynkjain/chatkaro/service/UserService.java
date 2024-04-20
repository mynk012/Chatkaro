package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.User;

import java.util.List;


public interface UserService {

    User registerUser(User user);

    User findUserById(Integer id) throws Exception;

    User findUserByEmail(String email);

    User followUser(Integer reqUserId, Integer followUserId) throws Exception;

    User updateUser(User user, Integer userId) throws Exception;
    List<User> searchUser(String query);

    public User findUserByJwt(String jwt);

}
