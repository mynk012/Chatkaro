package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Post;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.PostRepository;
import com.mynkjain.chatkaro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService{

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post createNewPost(Post post, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post newPost =new Post();

        newPost.setCaption(post.getCaption());
        newPost.setCreatedAt(LocalDateTime.now());
        newPost.setImage(post.getImage());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        Post createdPost =postRepository.save(newPost);

        return createdPost;
    }

    @Override
    public String deletePost(Integer postId, Integer userId) throws Exception {
        Post post = findPostById(postId);

        User user=userService.findUserById(userId);
//        System.out.println(post.getUser().getId()+" ------ "+user.getId());
        if(post.getUser().getId().equals(user.getId())) {
//            System.out.println("inside delete");
            postRepository.delete(post);

            return "Post Deleted Successfully";
        }


        throw new Exception("You Dont have access to delete this post");
    }

    @Override
    public List<Post> findPostByUserId(Integer userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostById(Integer postId) throws Exception {
        Optional<Post> opt = postRepository.findById(postId);
        if(opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("Post not exist with id: "+postId);
    }
    @Override
    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post savedPost(Integer postId, Integer userId) throws Exception {
        Post post=findPostById(postId);
        User user=userService.findUserById(userId);
        if(!user.getSavedPost().contains(post)) {
            user.getSavedPost().add(post);
        }
        else {
            user.getSavedPost().remove(post);

        }
        userRepository.save(user);
        return post;
    }

    @Override
    public Post likePost(Integer postId, Integer userId) throws Exception {

        User user= userService.findUserById(userId);

        Post post=findPostById(postId);

        if(!post.getLiked().contains(user)) {
            post.getLiked().add(user);
        }
        else {
            post.getLiked().remove(user);
        }

        return postRepository.save(post);

    }
}
