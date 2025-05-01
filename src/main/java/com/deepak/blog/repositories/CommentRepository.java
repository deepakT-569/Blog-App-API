 package com.deepak.blog.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.blog.entities.Comment;
import com.deepak.blog.entities.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	Set<Comment> findByPost(Post post);
	

}
