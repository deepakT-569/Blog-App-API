package com.deepak.blog.servicesImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.deepak.blog.entities.Category;
import com.deepak.blog.entities.Post;
import com.deepak.blog.entities.User;
import com.deepak.blog.exceptions.ResourceNotFoundException;
import com.deepak.blog.dto.PostDto;
import com.deepak.blog.response.PostResponse;
import com.deepak.blog.repositories.CategoryRepository;
import com.deepak.blog.repositories.CommentRepository;
import com.deepak.blog.repositories.PostRepositiory;
import com.deepak.blog.repositories.UserRepository;
import com.deepak.blog.services.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private PostRepositiory postRepositiory;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setPostAdded(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost = this.postRepositiory.save(post);
		return this.modelmapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepositiory.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		post.setPostTitle(postDto.getPostTitle() != null ? postDto.getPostTitle() : post.getPostTitle());
		post.setPostContent(postDto.getPostContent() != null ? postDto.getPostContent() : post.getPostContent());
		post.setImageName(postDto.getImageName() != null ? postDto.getImageName() : post.getImageName());
		Post savedPost = this.postRepositiory.save(post);
		return this.modelmapper.map(savedPost, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepositiory.findAll(pageable);
		List<Post> allPost = pagePost.getContent();
		List<PostDto> postDtoList = allPost.stream().map(postList -> this.modelmapper.map(postList, PostDto.class)).toList();
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtoList);
		System.out.println(postDtoList);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastpage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostsById(Integer postId) {
		Post post = this.postRepositiory.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post ID", postId));
		return this.modelmapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "User ID", userId));
		List<Post> userlist = this.postRepositiory.findByUser(user);
        return userlist.stream().map(userPost -> this.modelmapper.map(userPost, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsBycategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> post = this.postRepositiory.findByCategory(category);
        return post.stream().map(categoryList -> this.modelmapper.map(categoryList, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPostsByKeywords(String keyword) {
		List<Post> PostByKeyword = this.postRepositiory.searchByTitle("%"+keyword+"%");
        return PostByKeyword.stream().map((post)-> this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepositiory.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepositiory.delete(post);
	}
}
