package com.deepak.blog.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

	@NotEmpty
	private String postTitle;

	private String postContent;

	private String imageName;

	@CreationTimestamp
	@Column(name = "Created_On",updatable = false)
	private Date postAdded;

	@UpdateTimestamp
	@Column(name = "Modified_On")
	private Date postModified;

	private CategoryDto category;

	private UserDto user;

	private Set<CommentDto> comments=new HashSet<>();

}
