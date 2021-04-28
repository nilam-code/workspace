package com.example.socialMedia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.socialMedia.model.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
    @Override
    Optional<UserProfile> findById(Long userId);
}

