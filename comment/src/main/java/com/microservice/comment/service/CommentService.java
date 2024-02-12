package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplate;
    public Comment saveComment(Comment comment){
        Post post = restTemplate.getRestTemplate().getForObject("http://POST-SERVICE/8081/api/post/" + comment.getPostId(), Post.class);

        if (post!=null){
            String commentId = UUID.randomUUID().toString();
            comment.setCommentId(commentId);
            Comment saveComment = commentRepository.save(comment);
            return  saveComment;
        }else {
            return null;
        }


    }

    public List<Comment> getAllCommentsByPostId(String postId) {

        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }
}
