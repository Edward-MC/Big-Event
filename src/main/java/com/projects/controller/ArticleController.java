package com.projects.controller;

import com.projects.pojo.Article;
import com.projects.pojo.PageBean;
import com.projects.pojo.Result;
import com.projects.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    public Result add(@RequestBody Article article) {
      articleService.add(article);
      return Result.success();
    }

    @GetMapping
    public PageBean<Article> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return pb;
    }


    @GetMapping("/detail")
    public Result<Article> getDetail(@RequestParam Integer id) {
        Article article = articleService.getDetail(id);
        return Result.success(article);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Article article) {
        articleService.update(article);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam Integer id) {
        articleService.delete(id);
        return Result.success();
    }
}
