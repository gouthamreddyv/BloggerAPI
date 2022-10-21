package com.javaassignment.blog.BloggerAPI.controller;

import com.javaassignment.blog.BloggerAPI.APIException;
import com.javaassignment.blog.BloggerAPI.model.AdminResponse;
import com.javaassignment.blog.BloggerAPI.model.BlogPostReq;
import com.javaassignment.blog.BloggerAPI.model.BlogPostRes;
import com.javaassignment.blog.BloggerAPI.service.AdminService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

}
