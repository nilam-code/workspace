package com.thousandeyes.repositories;

public class Tweet
{
  private String content;
  private String name;

  public Tweet()
  {
    
  }

  public Tweet(String content, String name)
  {
    this.content = content;
    this.name = name;
  }

  public String getContent()
  {
    return this.content;  
  }

  public void setContent(String content)
  {
    this.content=content;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public String toString() {
      return String.format("Tweet[name='%s', content='%s']", name, content);
  }      
}