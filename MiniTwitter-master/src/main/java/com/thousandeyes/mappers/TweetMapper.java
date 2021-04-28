package com.thousandeyes.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.thousandeyes.repositories.Tweet;

public class TweetMapper implements RowMapper {  
 public Tweet mapRow(ResultSet rs, int rowNum) throws SQLException {  
  Tweet t = new Tweet();  
  t.setContent(rs.getString("content"));  
  t.setName(rs.getString("name")); 
  return t;  
 }  
}  