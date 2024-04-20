package com.mynkjain.chatkaro.service;

import com.mynkjain.chatkaro.model.Comments;
import com.mynkjain.chatkaro.model.Post;
import com.mynkjain.chatkaro.model.User;
import com.mynkjain.chatkaro.repository.CommentRepository;

import com.mynkjain.chatkaro.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CommentServiceImplementation implements CommentService{

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private PostRepository postRepository;

    @Override
    public Comments createComment(Comments comments, Integer postId, Integer userId) throws Exception {

        User user = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comments.setUser(user);
        comments.setContent(comments.getContent());
        comments.setCreatedAt(LocalDateTime.now());

        Comments savedComment = commentRepository.save(comments);

        post.getComments().add(savedComment);
        postRepository.save(post);

        return savedComment;
    }


    @Override
    public Comments findCommentById(Integer commentId) throws Exception {
        Optional<Comments> opt=commentRepository.findById(commentId);

        if(opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("comment not exist with id : "+commentId);

    }

    @Override
    public Comments likeComment(Integer commentId, Integer userId) throws Exception {

        Comments comments = findCommentById(commentId);
        User user = userService.findUserById(userId);

        if(!comments.getLiked().contains(user)){
            comments.getLiked().add(user);
        }
        else{
            comments.getLiked().remove(user);
        }
        return commentRepository.save(comments);
    }
}
