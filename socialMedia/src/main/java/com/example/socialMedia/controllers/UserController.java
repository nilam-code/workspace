package com.example.socialMedia.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.socialMedia.RestResponse;
import com.example.socialMedia.dto.PostDto;
import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.exception.ResourceNotFoundException;
import com.example.socialMedia.model.Post;
import com.example.socialMedia.model.User;
import com.example.socialMedia.repository.PostRepository;
import com.example.socialMedia.repository.UserRepository;
import com.example.socialMedia.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	UserService userService;
	
	 @PostMapping("/users/signup")
	    public RestResponse createUser(@Valid @RequestBody User user){

	        try{
	           return RestResponse.createSuccessResponse(userService.insertUser(user));
	        }
	        catch(CustomException e){
	            return RestResponse.createFailureResponse(e.getMessage(),400);
	        }

	   //    return RestResponse.createFailureResponse("User Already exists",400);

	        }

	    @GetMapping("/users/signin")
	    public RestResponse getUser(@Valid @RequestParam("email") String user){
	        try{
	           return RestResponse.createSuccessResponse(userService.fetchUserToken(user));

	        }
	        catch(ResourceNotFoundException e){
	            return RestResponse.createFailureResponse(e.getMessage(),400);
	        }

	    }


	    @GetMapping("/users/getall")
	    public List<PostDto> getAllPosts(@Valid @RequestParam("userProfId") Long userProfId){
	        List<Post> posts= new ArrayList<>();

	        return postRepository.findByUserProfileId(userProfId);



	    }

}
