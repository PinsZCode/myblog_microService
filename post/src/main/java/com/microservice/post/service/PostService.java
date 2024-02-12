package com.microservice.post.service;

import com.microservice.post.entity.Post;
import com.microservice.post.payLoad.PostDto;

public interface PostService {
    public Post savePost(Post post);

   public  Post findPostById(String postId);

    PostDto getPostWithComments(String postId);
}
