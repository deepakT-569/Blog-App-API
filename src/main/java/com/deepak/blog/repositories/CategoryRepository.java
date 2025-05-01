package com.deepak.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.blog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
