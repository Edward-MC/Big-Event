package com.projects.pojo;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Integer id;  // primary key
    private String username;
    @JsonIgnore  // Ignore the password when converting to Json Object
    private String password;

    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;
    private String userPic;  // User Profile Pic, only store link to third party server
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
