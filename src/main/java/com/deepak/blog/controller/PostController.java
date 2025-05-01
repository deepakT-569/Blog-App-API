package com.deepak.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.deepak.blog.config.AppConstants;
import com.deepak.blog.response.ApiResponse;
import com.deepak.blog.dto.PostDto;
import com.deepak.blog.response.PostResponse;
import com.deepak.blog.services.IFileService;
import com.deepak.blog.services.IPostService;

import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private IPostService postService;
	
	@Autowired
	private IFileService fileService;
	
	@Value("$(project-image)")
	private String path;

	// Post
	@PostMapping("/")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @RequestParam Integer userId,
			@RequestParam Integer categoryId) {
		return new ResponseEntity<PostDto>(this.postService.createPost(postDto, userId, categoryId),
				HttpStatus.CREATED);
	}

	// Put
	@PutMapping("/")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @RequestParam Integer postId) {
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto, postId), HttpStatus.OK);
	}

	// Get ALL Posts
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue  = AppConstants.PAGE_SIZE, required=false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required=false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
			) {
		return new ResponseEntity<PostResponse>(this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir), HttpStatus.OK);
	}

	// Get Post By postId
	@GetMapping("/postId-{postId}")
	public ResponseEntity<PostDto> getPostsById(@PathVariable Integer postId) {
		return new ResponseEntity<PostDto>(this.postService.getPostsById(postId), HttpStatus.OK);
	}

	// Delete Post
	@DeleteMapping("/")
	public ResponseEntity<?> deletePost(@RequestParam Integer postId) {
		this.postService.deletePost(postId);
		return new ResponseEntity<>(new ApiResponse("Post is delete successfully", true), HttpStatus.OK);
	}

	// Get Post By UserId
	@GetMapping("/userId-{userId}")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostsByUser(userId), HttpStatus.OK);
	}

	// Get Post by CategoryId
	@GetMapping("/categoryId-{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostsBycategory(categoryId), HttpStatus.OK);
	}

	/*// get search by Keywords
	@GetMapping("/posts/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostsByKeywords(@PathVariable String keyword) {
		return new ResponseEntity<List<PostDto>>(this.postService.searchPostsByKeywords(keyword), HttpStatus.OK);
	}


	//Image Upload
	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@PathVariable Integer postId, @RequestParam MultipartFile image) throws IOException
	{
		PostDto postDto = this.postService.getPostsById(postId);
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
	
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	//Image Download
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadPostImage(@PathVariable String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}*/
}
