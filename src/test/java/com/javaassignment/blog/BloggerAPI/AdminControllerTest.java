package com.javaassignment.blog.BloggerAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaassignment.blog.BloggerAPI.controller.AdminController;
import com.javaassignment.blog.BloggerAPI.model.BlogPostReq;
import com.javaassignment.blog.BloggerAPI.model.BlogPostRes;
import com.javaassignment.blog.BloggerAPI.model.PostDtls;
import com.javaassignment.blog.BloggerAPI.service.AdminService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JacksonAutoConfiguration.class)
public class AdminControllerTest {


    private MockMvc mockMvc;

    @MockBean
    private AdminService adminService;

    @InjectMocks
    private AdminController adminControler;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminControler)
                .build();
    }

    @Test
    public void test_getAllPosts() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/posts")
                )
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

    }

    @Test
    public void test_createPost() throws Exception {

        BlogPostRes blogPostRes = BlogPostRes.builder()
                .id(1)
                .userId(1)
                .title("title")
                .body("body")
                .build();
        Mockito.when(adminService.savePost(Mockito.any(BlogPostReq.class))).thenReturn(blogPostRes);

        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(blogPostRes)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.id",
                        Matchers.is(blogPostRes.getId())))
                .andExpect(jsonPath("$.userId",
                        Matchers.is(blogPostRes.getUserId())))
                .andExpect(jsonPath("$.title",
                        Matchers.is(blogPostRes.getTitle())))
                .andExpect(jsonPath("$.body",
                        Matchers.is(blogPostRes.getBody())));
    }

}
