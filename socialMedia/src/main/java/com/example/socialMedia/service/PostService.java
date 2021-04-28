package com.example.socialMedia.service;

import org.springframework.http.ResponseEntity;

import com.example.socialMedia.model.Post;

public interface PostService {

	 Post InsertPost(Post post,Long UserProfId);

	 Post EditPost(Long postId,Post post);

	 ResponseEntity<?> deletePost(Long PostId);
}
