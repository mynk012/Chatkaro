package com.mynkjain.chatkaro.service;


import com.mynkjain.chatkaro.model.Comments;

public interface CommentService {

    public Comments createComment(Comments comments, Integer postId, Integer userId) throws Exception;

    public Comments findCommentById(Integer commentId) throws Exception;
    public Comments likeComment(Integer commentId,Integer userId) throws Exception;

}
