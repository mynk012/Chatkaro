package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    public User registerUser(User user);

    public User findUserById(Integer id) throws Exception;

    public User findUserByEmail(String email);

    public User followUser(Integer reqUserId, Integer followUserId) throws Exception;

    public User updateUser(User user, Integer userId) throws Exception;
    public List<User> searchUser(String query);





}
