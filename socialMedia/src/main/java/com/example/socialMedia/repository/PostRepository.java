package com.example.socialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialMedia.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{
	
	
}
