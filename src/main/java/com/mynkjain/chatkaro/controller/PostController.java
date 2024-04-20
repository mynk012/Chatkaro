package com.mynkjain.chatkaro.controller;

import com.mynkjain.chatkaro.model.Post;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.response.ApiResponse;
import com.mynkjain.chatkaro.service.PostService;
import com.mynkjain.chatkaro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestHeader("Authorization") String jwt,@RequestBody Post post) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        Post createdPost = postService.createNewPost(post, reqUser.getId());

        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @GetMapping("{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post=postService.findPostById(postId);

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable Integer userId){
        List<Post> posts = postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> posts = postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @PutMapping("/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        Post post=postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);

    }

    @PutMapping("/likePost/{postId}")
    public ResponseEntity<Post> likePostHandler(@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        Post post=postService.likePost(postId, reqUser.getId());

        return new ResponseEntity<>(post, HttpStatus.ACCEPTED);

    }
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePost(
            @RequestHeader("Authorization") String jwt,@PathVariable Integer postId)
            throws Exception {

        User reqUser = userService.findUserByJwt(jwt);
        String message=postService.deletePost(postId, reqUser.getId());

        ApiResponse res = new ApiResponse(message,true);
        return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);
    }
}
