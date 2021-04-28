package com.example.socialMedia.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialMedia.RestResponse;
import com.example.socialMedia.exception.ResourceNotFoundException;
import com.example.socialMedia.model.Post;
import com.example.socialMedia.repository.PostRepository;
import com.example.socialMedia.repository.UserProfileRepository;
import com.example.socialMedia.service.PostService;


@RestController
public class PostController {

	@Autowired
    PostRepository postRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    PostService postService;
    
    
    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable)
    {
    	return postRepository.findAll(pageable);
    }
    
    
    @PostMapping("/posts/{userProfileId}")
    public RestResponse createPost(@Valid @RequestBody Post post, @PathVariable Long userProfId)
    {
    	try {
            postService.InsertPost(post, userProfId);
            return RestResponse.createSuccessResponse(postRepository.save(post));
        }

	    catch (ResourceNotFoundException e){
	            return RestResponse.createFailureResponse(e.getMessage(),400);
	    }
    }
    
    @PutMapping("/posts/{postId}")
    public RestResponse<?> updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {

        try{
            return RestResponse.createSuccessResponse(postService.EditPost(postId,postRequest));

        }catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    
    @DeleteMapping("/posts/{postId}")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {

        try{return RestResponse.createSuccessResponse(postService.deletePost(postId)); }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
    
    
}
