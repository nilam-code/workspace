package com.example.socialMedia.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.socialMedia.dto.PostDto;

public class PostRepositoryImpl implements PostRepositoryCustom{

	@PersistenceContext
	EntityManager entityManager;
	@Override
	public List<PostDto> findByUserProfileId(Long userProfId) {

		List<PostDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select p.id,p.title,p.description,p.content,u.id as userId from posts p,userprofile u where p.user_prof_id=u.id and p.user_prof_id=?","findAllDataMapping");
        query.setParameter(1, userProfId );
        resultList=query.getResultList();
        return resultList;
	}

}
