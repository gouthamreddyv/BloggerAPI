package com.javaassignment.blog.BloggerAPI.model;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "tbl_PostDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class PostDtls {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
    @Column(name = "ID", updatable = false, nullable = false)
    private Integer  id;
    @Column(name = "User_ID")
    private Integer userId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Body")
    private String body;

}