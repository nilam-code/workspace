package com.thousandeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.thousandeyes.mappers.*;
import com.thousandeyes.repositories.*;

import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class TwitterService
{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List following(String follower)
    {
      follower = "%"+follower+"%";
      String sql = "SELECT person.name, person.id from person inner join followers where person.id=followers.person_id and followers.follower_person_id in (SELECT id from person where name like :name)";
      return jdbcTemplate.query(sql, new MapSqlParameterSource("name", follower), new PersonMapper());            
    }


    public List followers(String person)
    {
      person = "%"+person+"%";
      String sql = "select person.name, person.id from person inner join followers where person.id=followers.follower_person_id and followers.person_id in (select id from person where name like :name)";
      return jdbcTemplate.query(sql, new MapSqlParameterSource("name", person), new PersonMapper());            
    }

    public int follow(String person, String follower)
    {
      int person_id = getID(person);
      int follower_person_id = getID(follower);
      if (person_id == follower_person_id)
        return 2; //same person and follower

      String sql = "select followers.person_id from followers where follower_person_id=:follower AND person_id=:person";
      Map m = new HashMap();
      m.put("person", person_id);
      m.put("follower", follower_person_id);
      List<Integer> result = jdbcTemplate.queryForList(sql, m, Integer.class);

      if(result.isEmpty())
      {
          sql = "INSERT INTO followers (person_id, follower_person_id) VALUES (:person, :follower)";
          return jdbcTemplate.update(sql, m);
      }
      
      else 
          return 0; //already following
    }

    public int unfollow(String person, String follower)
    {
      int person_id = getID(person);
      int follower_person_id = getID(follower);
      if (person_id == follower_person_id)
        return 2; //same person and follower

      String sql = "select followers.person_id from followers where follower_person_id=:follower AND person_id=:person";
      Map m = new HashMap();
      m.put("person", person_id);
      m.put("follower", follower_person_id);
      List<Integer> result = jdbcTemplate.queryForList(sql, m, Integer.class);

      if(!result.isEmpty())
      {
          sql = "DELETE from followers where person_id=:person AND follower_person_id=:follower";
          return jdbcTemplate.update(sql, m);
      }
      
      else 
          return 0; //already following            
    }

    public int getID(String person)
    {
      person = "%"+person+"%";
      String sql = "select name, id from person where name like :name";
      List ar = jdbcTemplate.query(sql, new MapSqlParameterSource("name", person), new PersonMapper());
      ArrayList<Person> foll = new ArrayList<Person>(ar);
      return foll.get(0).getID();
    }

    public List feed(String person, String search)
    {
      int person_id = getID(person);
      search="%"+search+"%";
      String sql = "select person.name, tweet.content from tweet inner join person where tweet.person_id = person.id AND (person.id=:id OR person.id in (select person_id from followers where follower_person_id=:id)) AND tweet.content like :search";
      Map m = new HashMap();
      m.put("search", search);
      m.put("id", person_id);
      return jdbcTemplate.query(sql, m, new TweetMapper());      
    }

    public String popularFollower()
    {
      String sql = "select id from person";
      List ar = jdbcTemplate.queryForList(sql, new HashMap(), Integer.class);
      ArrayList<Integer> id = new ArrayList<Integer>(ar);
      String s = "[";
      for (int i = 0; i < id.size(); i++)
      {
            String sql2 = "select person_id from followers where person_id in (select follower_person_id from followers where person_id=:id) group by person_id order by count(followers.person_id) desc limit 1";
            int result_id = jdbcTemplate.queryForObject(sql2, new MapSqlParameterSource("id", id.get(i)), Integer.class);
            sql2 = "select name from person where id=:id";
            if (i==id.size()-1)                                              
              s = s + "{\"User\": \"" + getName(id.get(i)) + "\", \"Popular Follower\": \"" + getName(result_id) + "\"}";
            else
              s = s + "{\"User\": \"" + getName(id.get(i)) + "\", \"Popular Follower\": \"" + getName(result_id) + "\"}, ";
      }
      s = s + "]";      
      return s;               
    }

    public String getName(int id)
    {
      String sql = "select name, id from person where id=:id";
      List ar = jdbcTemplate.query(sql, new MapSqlParameterSource("id", id), new PersonMapper());
      ArrayList<Person> foll = new ArrayList<Person>(ar);
      return foll.get(0).getName();
    }      
}