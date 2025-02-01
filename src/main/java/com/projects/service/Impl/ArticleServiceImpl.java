package com.projects.service.Impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.projects.mapper.ArticleMapper;
import com.projects.pojo.Article;
import com.projects.pojo.PageBean;
import com.projects.service.ArticleService;
import com.projects.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        // Supplement createUser, CreateTime and UpdateTime
        // Obtain CreateUser
        Map<String, Object> claims = ThreadLocalUtil.get();
        article.setCreateUser((Integer) claims.get("ID"));

        // Obtain CreateTime and UpdateTime
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // Use myBatis tool - PageHelper to query by page
        PageHelper.startPage(pageNum, pageSize);

        // Call Mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("ID");
        List<Article> res = articleMapper.list(userId, categoryId, state);
        Page<Article> p = (Page<Article>) res; // Force trans here since the subclass Page contains total count and items

        // Fill the data into PageBean Class
        PageBean<Article> pb = new PageBean<>();
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }

    @Override
    public Article getDetail(Integer id) {
        Article article = articleMapper.getDetail(id);
        return article;
    }

    @Override
    public void update(Article article) {
        // Update updateTime
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.update(article);
    }

    @Override
    public void delete(Integer id) {
        articleMapper.delete(id);
    }


}
