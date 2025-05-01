package com.deepak.blog.response;

import java.util.List;

import com.deepak.blog.dto.CategoryDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {
	
	private List<CategoryDto> content;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastpage;

}
