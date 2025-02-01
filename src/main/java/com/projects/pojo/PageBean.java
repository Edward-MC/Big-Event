package com.projects.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//Paging Result Object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class  PageBean <T>{
    private Long total;// Total Count
    private List<T> items;//Current page items
}
