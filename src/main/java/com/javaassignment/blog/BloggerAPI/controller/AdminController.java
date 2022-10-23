package com.javaassignment.blog.BloggerAPI.controller;

import com.javaassignment.blog.BloggerAPI.exception.APIException;
import com.javaassignment.blog.BloggerAPI.model.AdminResponse;
import com.javaassignment.blog.BloggerAPI.model.BlogPostReq;
import com.javaassignment.blog.BloggerAPI.model.BlogPostRes;
import com.javaassignment.blog.BloggerAPI.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Log
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping(value = "/posts", produces = {"Application/json"})
    public List<AdminResponse> getAllPosts() throws APIException {
        return adminService.getAllPostsWithUser();
    }

    @PostMapping(value = "/post")
    public BlogPostRes createPost(@Valid @RequestBody BlogPostReq blogPostReq) throws APIException {
        return adminService.savePost(blogPostReq);
    }

}
