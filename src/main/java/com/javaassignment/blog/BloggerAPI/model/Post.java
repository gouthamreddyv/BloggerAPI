package com.javaassignment.blog.BloggerAPI.model;

import lombok.Data;

@Data
public class Post {
    public int id;
    public int userId;
    public String title;
    public String body;
}

