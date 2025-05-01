package com.deepak.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepak.blog.entities.Category;
import com.deepak.blog.entities.Post;
import com.deepak.blog.entities.User;

public interface PostRepositiory extends JpaRepository<Post,Integer> {
	
	//Custom derived methods
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.postTitle like :key")
	List<Post> searchByTitle(@Param("key") String title);

}
