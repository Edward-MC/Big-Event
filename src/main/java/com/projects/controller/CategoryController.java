package com.projects.controller;

import com.projects.pojo.Category;
import com.projects.pojo.Result;
import com.projects.service.CategoryService;
import com.projects.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody @Validated(Category.add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list() {
        // Obtain ID from JWT
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer ID = (Integer) claims.get("ID");
        return Result.success(categoryService.list(ID));
    }

    @GetMapping("/detail")
    public Result<Category> getDetail(@RequestParam Integer id) {
        Category category = categoryService.findByID(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        categoryService.delete(id);
        return Result.success();
    }

}
