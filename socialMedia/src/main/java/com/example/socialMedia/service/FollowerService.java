package com.example.socialMedia.service;

public interface FollowerService {

	 public int follow(String person, String follower);
	 
	 public int unfollow(String person, String follower);
}
