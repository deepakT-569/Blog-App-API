package com.deepak.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tbl_posts")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title",nullable = false,length = 100)
	private String postTitle;
	
	@Column(name="post_content",nullable = false,length = 10000)
	private String postContent;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name = "post_added")
	private Date postAdded;
	
	@ManyToOne
	@JoinColumn(name ="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy="post",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private Set<Comment> comment = new HashSet<>();
}
