package com.javaassignment.blog.BloggerAPI.service;

import com.javaassignment.blog.BloggerAPI.exception.APIException;
import com.javaassignment.blog.BloggerAPI.model.AdminResponse;
import com.javaassignment.blog.BloggerAPI.model.BlogPostReq;
import com.javaassignment.blog.BloggerAPI.model.BlogPostRes;
import com.javaassignment.blog.BloggerAPI.model.PostDtls;
import com.javaassignment.blog.BloggerAPI.repository.PostDtlsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JacksonAutoConfiguration.class)
@TestPropertySource(locations="classpath:application-test.properties")
@ActiveProfiles("test")
public class AdminServiceTest {

    @Value("${user_list_uri}")
    private String user_list_uri;

    @Value("${post_uri}")
    private String post_uri;

    @MockBean
    private PostDtlsRepository postDtlsRepository;

    @MockBean
    private Environment env;

    @MockBean
    RestTemplate restTemplate;

    @InjectMocks
    private AdminService adminService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
     }

    @Test
    public void test_getAllPostsWithUser() throws APIException {

        String userList="[{\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"name\": \"Leanne Graham\",\n" +
                "\t\t\"username\": \"Bret\",\n" +
                "\t\t\"email\": \"Sincere@april.biz\",\n" +
                "\t\t\"address\": {\n" +
                "\t\t\t\"street\": \"Kulas Light\",\n" +
                "\t\t\t\"suite\": \"Apt. 556\",\n" +
                "\t\t\t\"city\": \"Gwenborough\",\n" +
                "\t\t\t\"zipcode\": \"92998-3874\",\n" +
                "\t\t\t\"geo\": {\n" +
                "\t\t\t\t\"lat\": \"-37.3159\",\n" +
                "\t\t\t\t\"lng\": \"81.1496\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"phone\": \"1-770-736-8031 x56442\",\n" +
                "\t\t\"website\": \"hildegard.org\",\n" +
                "\t\t\"company\": {\n" +
                "\t\t\t\"name\": \"Romaguera-Crona\",\n" +
                "\t\t\t\"catchPhrase\": \"Multi-layered client-server neural-net\",\n" +
                "\t\t\t\"bs\": \"harness real-time e-markets\"\n" +
                "\t\t}\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"name\": \"Ervin Howell\",\n" +
                "\t\t\"username\": \"Antonette\",\n" +
                "\t\t\"email\": \"Shanna@melissa.tv\",\n" +
                "\t\t\"address\": {\n" +
                "\t\t\t\"street\": \"Victor Plains\",\n" +
                "\t\t\t\"suite\": \"Suite 879\",\n" +
                "\t\t\t\"city\": \"Wisokyburgh\",\n" +
                "\t\t\t\"zipcode\": \"90566-7771\",\n" +
                "\t\t\t\"geo\": {\n" +
                "\t\t\t\t\"lat\": \"-43.9509\",\n" +
                "\t\t\t\t\"lng\": \"-34.4618\"\n" +
                "\t\t\t}\n" +
                "\t\t},\n" +
                "\t\t\"phone\": \"010-692-6593 x09125\",\n" +
                "\t\t\"website\": \"anastasia.net\",\n" +
                "\t\t\"company\": {\n" +
                "\t\t\t\"name\": \"Deckow-Crist\",\n" +
                "\t\t\t\"catchPhrase\": \"Proactive didactic contingency\",\n" +
                "\t\t\t\"bs\": \"synergize scalable supply-chains\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "]";
        String postList="[{\n" +
                "\t\t\"userId\": 1,\n" +
                "\t\t\"id\": 1,\n" +
                "\t\t\"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "\t\t\"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"userId\": 1,\n" +
                "\t\t\"id\": 2,\n" +
                "\t\t\"title\": \"qui est esse\",\n" +
                "\t\t\"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"userId\": 2,\n" +
                "\t\t\"id\": 11,\n" +
                "\t\t\"title\": \"et ea vero quia laudantium autem\",\n" +
                "\t\t\"body\": \"delectus reiciendis molestiae occaecati non minima eveniet qui voluptatibus\\naccusamus in eum beatae sit\\nvel qui neque voluptates ut commodi qui incidunt\\nut animi commodi\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"userId\": 2,\n" +
                "\t\t\"id\": 12,\n" +
                "\t\t\"title\": \"in quibusdam tempore odit est dolorem\",\n" +
                "\t\t\"body\": \"itaque id aut magnam\\npraesentium quia et ea odit et ea voluptas et\\nsapiente quia nihil amet occaecati quia id voluptatem\\nincidunt ea est distinctio odio\"\n" +
                "\t}\n" +
                "]" ;

        ResponseEntity<String> usrResp = new ResponseEntity<>(userList, HttpStatus.OK);
        ResponseEntity<String> postResp = new ResponseEntity<>(postList, HttpStatus.OK);
        Mockito.when(restTemplate.getForEntity(Mockito.eq(user_list_uri),Mockito.eq(String.class))).thenReturn(usrResp);
        Mockito.when(restTemplate.getForEntity(Mockito.eq(post_uri),Mockito.eq(String.class))).thenReturn(postResp);
        Mockito.when(env.getProperty("user_list_uri")).thenReturn(user_list_uri);
        Mockito.when(env.getProperty("post_uri")).thenReturn(post_uri);
        List<AdminResponse> finallist =adminService.getAllPostsWithUser();
        assertNotNull(finallist);

        assertEquals(finallist.get(0).getUser().getId().intValue(),finallist.get(0).getPostList().get(0).getUserId());
        assertNotNull(finallist.get(0).getPostList());

        assertEquals(finallist.get(1).getUser().getId().intValue(),finallist.get(1).getPostList().get(0).getUserId());
        assertNotNull(finallist.get(1).getPostList());

        verify(restTemplate).getForEntity(user_list_uri, String.class);
        verify(restTemplate).getForEntity(post_uri, String.class);
        verify(env).getProperty("user_list_uri");
        verify(env).getProperty("post_uri");

    }

    @Test
    public void test_savePost() throws APIException {

        PostDtls postDtls = PostDtls.builder()
                .id(1)
                .userId(1)
                .title("title")
                .body("body")
                .build();
        BlogPostReq blogPostReq = BlogPostReq.builder()
                .userId(1)
                .title("title")
                .body("body")
                .build();
        ResponseEntity<BlogPostReq> respEntity = new ResponseEntity<>(blogPostReq, HttpStatus.OK);
        Mockito.when(postDtlsRepository.save(Mockito.any(PostDtls.class))).thenReturn(postDtls);
        Mockito.when(restTemplate.postForEntity(Mockito.any(String.class),Mockito.any(BlogPostReq.class),Mockito.eq(BlogPostReq.class))).thenReturn(respEntity);
        Mockito.when(env.getProperty("post_uri")).thenReturn(post_uri);

        BlogPostRes blogPostRes =adminService.savePost(blogPostReq);
        assertEquals(blogPostRes.getUserId(),blogPostReq.getUserId());
        assertEquals(blogPostRes.getTitle(),blogPostReq.getTitle());
        assertEquals(blogPostRes.getBody(),blogPostReq.getBody());
        assertNotNull(blogPostRes.getId());
        verify(postDtlsRepository).save(postDtls);
        verify(restTemplate).postForEntity(post_uri, blogPostReq, BlogPostReq.class);
        verify(env).getProperty("post_uri");

    }
}