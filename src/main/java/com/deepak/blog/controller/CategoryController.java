package com.deepak.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.blog.config.AppConstants;
import com.deepak.blog.response.ApiResponse;
import com.deepak.blog.dto.CategoryDto;
import com.deepak.blog.response.CategoryResponse;
import com.deepak.blog.services.ICategoryService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	public ICategoryService categoryService;

	// Post
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(this.categoryService.createCategory(categoryDto),HttpStatus.CREATED);
	}

	// Put
	@PutMapping("/")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @RequestParam Integer categoryId) {
		return new ResponseEntity<CategoryDto>(this.categoryService.updateCategory(categoryDto, categoryId),
				HttpStatus.CREATED);
	}

	// Get All
	@GetMapping("/")
	public ResponseEntity<CategoryResponse> getAllCategory(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required=false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
			) {
		return new ResponseEntity<CategoryResponse>(this.categoryService.getAllCategory(pageNumber,pageSize), HttpStatus.OK);
	}

	// Get by ID
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getById(@PathVariable Integer categoryId) {
		return new ResponseEntity<CategoryDto>(this.categoryService.getByID(categoryId), HttpStatus.OK);
	}

	// Delete
	@DeleteMapping("/")
	public ResponseEntity<?> deleteCategory(@RequestParam Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category delete successfully", true), HttpStatus.OK);
	}
}
