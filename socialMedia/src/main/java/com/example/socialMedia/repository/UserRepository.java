package com.example.socialMedia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.socialMedia.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Override
	Optional<User> findById(Long userId);
	
	boolean existsByEmail(String email);
	
    User findByEmail(String email);
    
    @Query(value = "select * from user u where u.email=:email" , nativeQuery = true)
    Optional<User> getUserByEMail(@Param("email") String email);
    
}
