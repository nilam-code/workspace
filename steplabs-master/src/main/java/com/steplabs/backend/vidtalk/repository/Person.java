package com.thousandeyes.repositories;

public class Person
{
  private String name;
  private int id;

  public Person()
  {
    
  }

  public Person(String name, int id)
  {
    this.name = name;
    this.id = id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getID()
  {
    return this.id;  
  }

  public void setID(int id)
  {
    this.id=id;
  }

  @Override
  public String toString() {
      return String.format("Person[id=%d, name='%s']",id, name);
  }      
}