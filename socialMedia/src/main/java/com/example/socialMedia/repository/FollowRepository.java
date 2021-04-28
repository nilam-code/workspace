package com.example.socialMedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialMedia.model.Followers;

@Repository
public interface FollowRepository extends JpaRepository<Followers, Long>{

	int save(String sql);

}
