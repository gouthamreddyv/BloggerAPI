package com.javaassignment.blog.BloggerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class BlogPostRes {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;

}
