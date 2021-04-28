package com.example.socialMedia.repository;

import java.util.List;

import com.example.socialMedia.dto.PostDto;

public interface PostRepositoryCustom {
	 List<PostDto> findByUserProfileId(Long UserProdId);
}
