package com.projects.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    private Integer id;  // primary key
    private String categoryName;
    private String categoryAlias;
    private Integer createUser;     //Creator User ID
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
