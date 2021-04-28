package com.example.socialMedia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.socialMedia.exception.ResourceNotFoundException;
import com.example.socialMedia.model.Post;
import com.example.socialMedia.model.UserProfile;
import com.example.socialMedia.repository.PostRepository;
import com.example.socialMedia.repository.UserProfileRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	UserProfileRepository userProfileRepository;
	
	@Autowired
	PostRepository postRepository;

	@Override
	public Post InsertPost(Post post, Long userProfId) {
		Optional<UserProfile> userProfile = userProfileRepository.findById(userProfId);
		
		if(userProfile != null)
		{
			post.setUserProfile(userProfile.get());
			return post;
		}
		else{throw new ResourceNotFoundException("User Profile Is Not Found");
        }
	}

	@Override
	public Post EditPost(Long postId, Post post) {
		return postRepository.findById(postId).map(Updatedpost -> {
            Updatedpost.setTitle(post.getTitle());
            Updatedpost.setDescription(post.getDescription());
            Updatedpost.setContent(post.getContent());
            return postRepository.save(Updatedpost);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	}

	@Override
	public ResponseEntity<?> deletePost(Long postId) {
		 return postRepository.findById(postId).map(post -> {
	            postRepository.delete(post);
	            return ResponseEntity.ok().build();
	        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
	    }

}
