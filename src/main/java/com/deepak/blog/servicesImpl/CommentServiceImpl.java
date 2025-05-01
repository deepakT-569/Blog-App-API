package com.deepak.blog.servicesImpl;

import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepak.blog.entities.Comment;
import com.deepak.blog.entities.Post;
import com.deepak.blog.exceptions.ResourceNotFoundException;
import com.deepak.blog.dto.CommentDto;
import com.deepak.blog.repositories.CommentRepository;
import com.deepak.blog.repositories.PostRepositiory;
import com.deepak.blog.services.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepositiory postRepositiory;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepositiory.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		Comment comment = this.modelmapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment = this.commentRepository.save(comment);
		return this.modelmapper.map(savedComment, CommentDto.class);
	}
	@Override
	public Set<CommentDto> getAllCommentsByPost() {
        return this.commentRepository.findAll().stream()
				.map(list -> this.modelmapper.map(list,CommentDto.class)).collect(Collectors.toSet());
	}
	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		this.commentRepository.delete(comment);
	}
	@Override
	public CommentDto getCommentById(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment","commentId",commentId));
		return this.modelmapper.map(comment,CommentDto.class);
	}

}
