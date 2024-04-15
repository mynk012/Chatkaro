package com.mynkjain.chatkaro.repository;

import com.mynkjain.chatkaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {


    public User findByEmail(String email); // we have to create it as by default it's not there

    @Query("select u from User u where u.firstName LIKE CONCAT('%', :query, '%') OR u.lastName LIKE CONCAT('%', :query, '%') OR u.email LIKE CONCAT('%', :query, '%')")
    public List<User> searchUser(@Param("query") String query);
}
