package com.projects.controller;

import com.projects.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @RequestMapping("/list")
    public Result<String> list() {
        // Verify Token
        return Result.success("All Article Lists Returned");
    }
}
