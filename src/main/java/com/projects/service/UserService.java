package com.projects.service;

import com.projects.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    // Update User's Account Image
    void updateAvatar(String avatarUrl);

    void updatePsw(String newPsw);
}
