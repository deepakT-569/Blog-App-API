package com.deepak.blog.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deepak.blog.response.ApiResponse;
import com.deepak.blog.dto.CommentDto;
import com.deepak.blog.services.ICommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	@Autowired
	private ICommentService commentService;
	// post
	@PostMapping("/")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam Integer postId) {
		CommentDto createComment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(createComment, HttpStatus.OK);

	}
	//get all by Post
	@GetMapping("/")
	public ResponseEntity<Set<CommentDto>> getAllCommentsByPost()
	{
		Set<CommentDto> allCommentsByPost = this.commentService.getAllCommentsByPost();
		return new ResponseEntity<Set<CommentDto>>(allCommentsByPost,HttpStatus.OK);
	}
	// get by comment Id
	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDto> getCommentByCommentId (@PathVariable Integer commentId) {
		CommentDto commentById = this.commentService.getCommentById(commentId);
		return new ResponseEntity<>(commentById,HttpStatus.OK);
	}
	// delete
	@DeleteMapping("/")
	public ResponseEntity<?> deleteComment(@RequestParam Integer commentId) {
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse("Comment deleted Successfully", true), HttpStatus.OK);
	}
}
