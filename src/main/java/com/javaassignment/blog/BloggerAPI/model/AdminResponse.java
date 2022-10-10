package com.javaassignment.blog.BloggerAPI.model;

import lombok.Data;

import java.util.List;

@Data
public class AdminResponse {

    User user;
    List<Post> postList;
}
