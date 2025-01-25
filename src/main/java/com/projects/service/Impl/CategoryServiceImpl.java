package com.projects.service.Impl;

import com.projects.mapper.CategoryMapper;
import com.projects.pojo.Category;
import com.projects.service.CategoryService;
import com.projects.utils.ThreadLocalUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        // Supplement the Data: createUser, createTime, updateTime

        // createUser
        Map<String, Object> claims = ThreadLocalUtil.get();
        category.setCreateUser((Integer) claims.get("ID"));

        // createTime, updateTime
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        categoryMapper.add(category);
    }

    @Override
    public List<Category> list(Integer ID) {
        return categoryMapper.list(ID);
    }

    @Override
    public Category findByID(Integer id) {
        return categoryMapper.findByID(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
