package com.javaassignment.blog.BloggerAPI.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogPostReq {
    @NotNull
    private Integer userId;
    @NotBlank(message = "title cannot be Empty")
    private String title;
    @NotBlank(message = "body cannot be Empty")
    private String body;
}
