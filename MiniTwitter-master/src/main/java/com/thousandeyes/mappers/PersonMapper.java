package com.thousandeyes.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.thousandeyes.repositories.Person;

public class PersonMapper implements RowMapper {  
 public Person mapRow(ResultSet rs, int rowNum) throws SQLException {  
  Person p = new Person();  
  p.setName(rs.getString("name"));  
  p.setID(rs.getInt("id")); 
  return p;  
 }  
}