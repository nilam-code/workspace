package com.example.socialMedia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.socialMedia.service.FollowerService;

public class FollowerController {
	
	@Autowired
	FollowerService followerService;
	
	 @RequestMapping(value = "/follow", method = { RequestMethod.PUT, RequestMethod.POST })
	    public String follow(@RequestParam(value="name", required=true) String name) {

	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        if (followerService.follow(name, username) == 1)
	          return "{\"status\": \"Success\", \"message\": \"You have followed the user successfully\"} ";
	        else if (followerService.follow(name, username) == 2)
	          return "{\"status\": \"Error\", \"message\": \"You cannot follow yourself\"}";
	        else if (followerService.follow(name, username) == 0)
	          return "{\"status\": \"Error\", \"message\": \"You already follow the user\"}";
	        else
	          return "{\"status\": \"Error\", \"message\": \"None\"}";       
	    }

	    @RequestMapping(value = "/unfollow", method = RequestMethod.PUT)
	    public String unfollow(@RequestParam(value="name", required=true) String name) {

	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        if (followerService.unfollow(name, username) == 1)
	          return "{\"status\": \"Success\", \"message\": \"You have unfollowed the user successfully\"} ";
	        else if (followerService.follow(name, username) == 2)
	          return "{\"status\": \"Error\", \"message\": \"You cannot unfollow yourself\"}";
	        else if (followerService.follow(name, username) == 0)
	          return "{\"status\": \"Error\", \"message\": \"You need to follow the user to unfollow\"}";
	        else
	          return "{\"status\": \"Error\", \"message\": \"None\"}";     
	    }

}
