package com.mynkjain.chatkaro.repository;

import com.mynkjain.chatkaro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
