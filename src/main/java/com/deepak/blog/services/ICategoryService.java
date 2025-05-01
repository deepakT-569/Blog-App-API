package com.deepak.blog.services;

import com.deepak.blog.dto.CategoryDto;
import com.deepak.blog.response.CategoryResponse;

public interface ICategoryService {
	
	//create category
	
	CategoryDto createCategory(CategoryDto categoryDto); 
	
	//update category
	
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//get all category
	
	CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize);
	
	//get by id category
	
	CategoryDto getByID(Integer CatgeoryId); 
	
	//delete category
	
	void deleteCategory(Integer categoryId);
}
