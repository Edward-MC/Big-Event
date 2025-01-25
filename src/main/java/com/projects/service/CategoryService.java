package com.projects.service;

import com.projects.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list(Integer id);

    Category findByID(Integer id);

    void update(Category category);

    void delete(Integer id);
}
