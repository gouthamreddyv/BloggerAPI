package com.javaassignment.blog.BloggerAPI.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaassignment.blog.BloggerAPI.APIException;
import com.javaassignment.blog.BloggerAPI.model.*;
import com.javaassignment.blog.BloggerAPI.repository.PostDtlsRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log
@PropertySource("classpath:application.properties")

public class AdminService {
    @Autowired
    private Environment env;
    @Autowired
    private PostDtlsRepository postDtlsRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<AdminResponse> getAllPostsWithUser() throws APIException {

        log.info("calling API for users ");
        String userdtls = getResponseFromAPI(env.getProperty("user_list_uri"));
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            users = mapper.readValue(userdtls, new TypeReference<List<User>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new APIException("Technical issue plz try again");
        }

        log.info("calling API for posts ");
        String postdtls = getResponseFromAPI(env.getProperty("post_uri"));
        List<Post> posts = null;
        try {
            posts = mapper.readValue(postdtls, new TypeReference<List<Post>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new APIException("Technical issue plz try again");
        }

        log.info("Prepare final list for admin user");

        return prepareResponse(users, posts);
    }

    public BlogPostRes savePost(BlogPostReq blogPostReq ) throws APIException {
        ModelMapper modelMapper = new ModelMapper();
        postWithURI(blogPostReq);
        PostDtls postObj = postDtlsRepository.save(modelMapper.map(blogPostReq,PostDtls.class));
        log.info("Object saved with id" + postObj.getId());
        return modelMapper.map(postObj, BlogPostRes.class);
    }
    private List<AdminResponse> prepareResponse(List<User> userList, List<Post> postlist) {

        List<AdminResponse> finallist = new ArrayList<>();
        userList.stream().forEach(user -> {
            List<Post> pstlist = postlist.stream().filter(post -> post.getUserId() == user.getId()).collect(Collectors.toList());
            AdminResponse adminResponse = new AdminResponse();
            adminResponse.setUser(user);
            adminResponse.setPostList(pstlist);
            finallist.add(adminResponse);
        });
        log.info("final list " + finallist);
        return finallist;
    }


    private String getResponseFromAPI(String uri) throws APIException {

        ResponseEntity response = null;
        try {
            response = restTemplate.getForEntity(new URL(uri).toString(), String.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new APIException("Technical issue plz try again");
        }
        if (!response.getStatusCode().is2xxSuccessful())
            throw new APIException("Technical issue plz try again");

        return response.getBody().toString();
    }

    private void postWithURI(BlogPostReq blogPostReq) throws APIException {

        ResponseEntity response = null;
        try {
            response = restTemplate.postForEntity(new URL(env.getProperty("post_uri")).toString(), blogPostReq, BlogPostReq.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new APIException("Technical issue plz try again");
        }
        if (!response.getStatusCode().is2xxSuccessful())
            throw new APIException("Technical issue plz try again");
    }


}
