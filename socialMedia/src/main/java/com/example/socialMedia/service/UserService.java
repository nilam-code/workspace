package com.example.socialMedia.service;

import com.example.socialMedia.model.User;

public interface UserService {

	String insertUser(User user);
    String fetchUserToken(String user);
    boolean alreadyRegistered(String email);
}
