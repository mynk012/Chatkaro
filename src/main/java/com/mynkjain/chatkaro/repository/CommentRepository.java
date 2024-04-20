package com.mynkjain.chatkaro.repository;

import com.mynkjain.chatkaro.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Integer> {

}
