package com.javaassignment.blog.BloggerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostReq {
    private Integer userId;
    private String title;
    private String body;
}
