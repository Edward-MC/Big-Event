package com.projects.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;  // primary key
    private String title;
    private String content;
    private String coverImg;
    private String state;   // Publish State: Published|Draft
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
