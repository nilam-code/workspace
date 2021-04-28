package com.example.socialMedia.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.socialMedia.model.User;
import com.example.socialMedia.repository.FollowRepository;

public class FollowerServiceImpl implements FollowerService{

	 @PersistenceContext
	    EntityManager entityManager;
	 
	 @Autowired
	 FollowRepository followRepository;
	 
	 @Autowired
	    private NamedParameterJdbcTemplate jdbcTemplate;
	 
	@Override
	public int follow(String person, String follower) {
		Long person_id = getID(person);
	      Long follower_person_id = getID(follower);
	      if (person_id == follower_person_id)
	        return 2; //same person and follower

	      String sql = "select followers.person_id from followers where follower_person_id=:follower AND person_id=:person";
	      Map m = new HashMap();
	      m.put("person", person_id);
	      m.put("follower", follower_person_id);
	      Query query = entityManager.createNativeQuery(sql,"findAllDataMapping");
	      List<Integer> result = query.getResultList();

	      if(result.isEmpty())
	      {
	    	  
	    	  sql = "INSERT INTO followers (person_id, follower_person_id) VALUES (:person, :follower)";
	          return jdbcTemplate.update(sql, m);
	      }
	      
	      else 
	          return 0; //already following
	}

	@Override
	public int unfollow(String person, String follower) {
		 Long person_id = getID(person);
	      Long follower_person_id = getID(follower);
	      if (person_id == follower_person_id)
	        return 2; //same person and follower

	      String sql = "select followers.person_id from followers where follower_person_id=:follower AND person_id=:person";
	      Map m = new HashMap();
	      m.put("person", person_id);
	      m.put("follower", follower_person_id);
	      Query query = entityManager.createNativeQuery(sql,"findAllDataMapping");
	      List<Integer> result = query.getResultList();

	      if(!result.isEmpty())
	      {
	          sql = "DELETE from followers where person_id=:person AND follower_person_id=:follower";
	          return jdbcTemplate.update(sql, m);
	      }
	      
	      else 
	          return 0; //already following    
	}
	
	public Long getID(String user)
    {
		user = "%"+user+"%";
      String sql = "select name, id from userProfile where name like :name";
      Query query = entityManager.createNativeQuery(sql,"findAllDataMapping");
      query.setParameter(1, user );
      List ar = query.getResultList();
      ArrayList<User> foll = new ArrayList<User>(ar);
      return foll.get(0).getId();
    }

}
