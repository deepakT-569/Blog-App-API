package com.deepak.blog.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.deepak.blog.entities.Category;
import com.deepak.blog.exceptions.ResourceNotFoundException;
import com.deepak.blog.dto.CategoryDto;
import com.deepak.blog.response.CategoryResponse;
import com.deepak.blog.repositories.CategoryRepository;
import com.deepak.blog.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private ModelMapper modelmapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		/*Category category = modelmapper.map(categoryDto, Category.class);*/
		Category category = new Category();
		BeanUtils.copyProperties(categoryDto,category);
		Category saveCategory = this.categoryRepository.save(category);
		return this.modelmapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDto.getCategoryTitle() != null ? categoryDto.getCategoryTitle() : category.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription() != null ? categoryDto.getCategoryDescription() : category.getCategoryDescription());
		Category saveCategory = this.categoryRepository.save(category);
		return this.modelmapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryResponse getAllCategory(Integer pageNumber,Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber,pageSize);
		Page<Category> pageCategory = this.categoryRepository.findAll(pageable);
		List<Category> allPosts = pageCategory.getContent();
		List<CategoryDto> categoryDtoList = allPosts.stream().map(list -> this.modelmapper.map(list, CategoryDto.class)).toList();
	    CategoryResponse categoryResponse = new CategoryResponse();
	    categoryResponse.setContent(categoryDtoList);
	    categoryResponse.setPageNumber(pageCategory.getNumber());
	    categoryResponse.setPageSize(pageCategory.getSize());
	    categoryResponse.setTotalElements(pageCategory.getNumberOfElements());
	    categoryResponse.setTotalPages(pageCategory.getTotalPages());
	    categoryResponse.setLastpage(pageCategory.isLast());
		return categoryResponse;
	}

	@Override
	public CategoryDto getByID(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));
		return this.modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepository.delete(category);
	}
}
