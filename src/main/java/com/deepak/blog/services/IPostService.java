package com.deepak.blog.services;

import java.util.List;

import com.deepak.blog.dto.PostDto;
import com.deepak.blog.response.PostResponse;

public interface IPostService {
	
	//create 
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//get all Posts 
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortby,String sortDir);
	
	//get single Post
	PostDto getPostsById(Integer postId);
		
	//get posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	// get posts by category
	List<PostDto> getPostsBycategory(Integer categoryId);
	
	//searchPost
	List<PostDto> searchPostsByKeywords(String keyword);
	
	//delete
	void deletePost(Integer postId);

}
