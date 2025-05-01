package com.deepak.blog.services;

import java.util.Set;

import com.deepak.blog.dto.CommentDto;

public interface ICommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

	Set<CommentDto> getAllCommentsByPost();

	CommentDto getCommentById(Integer commentId);
}
