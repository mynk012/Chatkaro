package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Comments;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.service.CommentService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/{postId}")
    public Comments createCommentHandler(
            @RequestBody Comments comment, @PathVariable("postId") Integer postId,
            @RequestHeader("Authorization")String token) throws Exception {
        User user = userService.findUserByJwt(token);

        Comments createdComment = commentService.createComment(comment, postId, user.getId());
        return createdComment;

    }

    @PutMapping("/like/{commentId}")
    public Comments likeCommentHandler(
            @PathVariable Integer commentId, @RequestHeader("Authorization")String token) throws Exception {

        User user = userService.findUserByJwt(token);
        Comments likedComment=commentService.likeComment(commentId, user.getId());
        return likedComment;
    }

}
