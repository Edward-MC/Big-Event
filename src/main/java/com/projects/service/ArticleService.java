package com.projects.service;

import com.projects.pojo.Article;
import com.projects.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    Article getDetail(Integer id);

    void update(Article article);

    void delete(Integer id);
}
