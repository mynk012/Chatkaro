package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Post;
import com.mynkjain.chatkaro.response.ApiResponse;
import com.mynkjain.chatkaro.service.PostService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createNewPost(@RequestBody Post post,@PathVariable Integer userId) throws Exception {

        Post createdPost = postService.createNewPost(post, userId);

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId){
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/post/save/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        Post post=postService.savedPost(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);

    }

    @PutMapping("/likePost/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {

        Post post=postService.likePost(postId, userId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/delete/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @PathVariable Integer userId) throws Exception {
        String message=postService.deletePost(postId, userId);

        ApiResponse res = new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }
}
