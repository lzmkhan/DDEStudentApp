package com.example.studentapp;

public class Items
{
  private String description;
  private String title;
  
  public Items(String paramString1, String paramString2)
  {
    this.title = paramString1;
    this.description = paramString2;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  
  public String getTitle()
  {
    return this.title;
  }
}
