package com.projects.pojo;



// Define Response Fields

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;   //Result Code: 0-Success, 1-Fail
    private String message;
    private T data; // Response Data, Use as Template Class in Convenient to Transfer to Other Format like: String, Object etc.

    // Return with Response Data
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "Success", data);
    }

    // Return without Response Data
    public static Result success() {
        return new Result(0, "Success", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
