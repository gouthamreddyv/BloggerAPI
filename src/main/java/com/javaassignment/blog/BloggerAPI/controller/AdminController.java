package com.javaassignment.blog.BloggerAPI.controller;

import com.javaassignment.blog.BloggerAPI.APIException;
import com.javaassignment.blog.BloggerAPI.model.AdminResponse;
import com.javaassignment.blog.BloggerAPI.model.BlogPostRes;
import com.javaassignment.blog.BloggerAPI.model.PostDtls;
import com.javaassignment.blog.BloggerAPI.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public BlogPostRes createPost(@RequestBody PostDtls postDtls) throws APIException {
        return adminService.savePost(postDtls);
    }

}
