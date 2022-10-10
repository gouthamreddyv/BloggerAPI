package com.javaassignment.blog.BloggerAPI.model;

import lombok.Data;


@Data
public class BlogPostReq {
    private Integer userId;
    private String title;
    private String body;
}
