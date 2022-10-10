package com.javaassignment.blog.BloggerAPI.repository;


import com.javaassignment.blog.BloggerAPI.model.PostDtls;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("postDtlsRepository")
public interface PostDtlsRepository extends CrudRepository<PostDtls,Integer > {
}