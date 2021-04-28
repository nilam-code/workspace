package com.example.socialMedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.socialMedia.exception.CustomException;
import com.example.socialMedia.exception.ResourceNotFoundException;
import com.example.socialMedia.model.User;
import com.example.socialMedia.repository.UserRepository;
import com.example.socialMedia.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	 JwtTokenProvider jwtTokenProvider;
	
	@Override
	public String insertUser(User user) {

		boolean flag;
		
		flag = alreadyRegistered(user.getEmail());
		if(flag != true)
		{
			user.getUserProfile().setUser(user);
			userRepository.save(user);
			return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
		}
		 else
        {
            throw new CustomException("User is already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
	}

	@Override
	public String fetchUserToken(String email) {
		 User user;
       user=userRepository.findByEmail(email);
      // return user.getEmail();
		
		boolean flag;
		flag = alreadyRegistered(email);
		if (flag) {
			user = getUser(email);
			return jwtTokenProvider.createToken(user.getEmail(), user.getRoles());
		} else {
			throw new ResourceNotFoundException("User is not registered");
		}

	}
	
	public User getUser(String email) {

	      User user;
	      user= userRepository.findByEmail(email);
	      return user;
	  }

	@Override
	public boolean alreadyRegistered(String email) {
		  return userRepository.existsByEmail(email);
	}

}
