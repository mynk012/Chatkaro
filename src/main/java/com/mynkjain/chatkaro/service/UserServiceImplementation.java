package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.config.JwtProvider;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// business logic will come in this file
@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User newUser = new User();

        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassword(user.getPassword());
        newUser.setId(user.getId());

        User savedUser=userRepository.save(newUser);
        return savedUser;
    }

    @Override
    public User findUserById(Integer userId) throws Exception {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("user not exist with userid" + userId);
    }

    @Override
    public User findUserByEmail(String email) {

        User user = userRepository.findByEmail(email);   //check in UserRepository we have created this method
        return user;
    }

    @Override
    public User followUser(Integer reqUserId, Integer userId) throws Exception {

        User reqUser = findUserById(reqUserId);
        User user = findUserById(userId);

        user.getFollowers().add(reqUser.getId());
        reqUser.getFollowings().add(user.getId());

        userRepository.save(reqUser); //.save use to save data in database
        userRepository.save(user);
        return reqUser;
    }

    public User updateUser(User user, Integer userId) throws Exception {
        Optional<User> user1 = userRepository.findById(userId);

        if(user1.isEmpty()){
            throw new Exception("user not exit with id" + userId);
        }

        User oldUser = user1.get();

        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }

        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }

        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }

        User updatedUser=userRepository.save(oldUser);

        return updatedUser;
    }

    @Override
    public List<User> searchUser(String query){

        return userRepository.searchUser(query);
    }

    @Override
    public User findUserByJwt(String jwt) {

        String email = JwtProvider.getEmailFromJwtToken(jwt);
        User user = userRepository.findByEmail(email);
        return user;
    }


}
