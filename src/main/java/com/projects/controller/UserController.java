package com.projects.controller;

import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import com.projects.pojo.Result;
import com.projects.pojo.User;
import com.projects.service.UserService;
import com.projects.utils.JwtUtil;
import com.projects.utils.Md5Util;
import com.projects.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // Check if username is valid
//        if (username == null || username.length() < 5 || username.length() > 16 ||
//            password == null || password.length() < 5 || password.length() > 16) {
//            return Result.error("Username or Password Invalid");
//        }

        // Check if username already exist
        User userDB = userService.findByUsername(username);
        if (userDB != null) return Result.error("Username Already been Occupied");

        // Do Register
        userService.register(username, password);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // Check if user exist
        User loginUser = userService.findByUsername(username);
        if (loginUser == null) {
            return Result.error("User Not Exist");
        }

        // Check if password matches
        if (loginUser.getPassword().equals(Md5Util.getMD5String(password))) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("ID", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String JWTToken = JwtUtil.genToken(claims);
            return Result.success(JWTToken);
        }
        return Result.error("Password doesn't Match");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/) {
//        Map<String, Object> claims = JwtUtil.parseToken(token);
//        String username = (String) claims.get("username");
        // Load from Thread Local Instead of Repeating Loading
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePsw")
    public Result updatePsw(@RequestBody Map<String, String> params) {
        // MVC will Make the Json Format Containing (old_psw, new_psw, re_psw) to Map
        // 1. Check the parameters are All not Null
        String oldPsw = params.get("old_psw");
        String newPsw = params.get("new_psw");
        String rePsw = params.get("re_psw");
        if (!StringUtils.hasLength(oldPsw) || !StringUtils.hasLength(newPsw) || !StringUtils.hasLength(rePsw)) {
            return Result.error("Missing Parameters!");
        }

        // 2. Check if the new Psw and re-typed Psw Match
        if (!newPsw.equals(rePsw)) {
            return Result.error("New Password Are Not The Same With Re-Typed Password!");
        }

        // 3. Check If Old Password is Correct
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPsw))) {
            return Result.error("Old Password Are Not Right!");
        }

        // 4. Call Service to Update the Password
        userService.updatePsw(newPsw);
        return Result.success();
    }

}
