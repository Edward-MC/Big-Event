package com.projects.service.Impl;

import com.projects.mapper.UserMapper;
import com.projects.pojo.User;
import com.projects.service.UserService;
import com.projects.utils.Md5Util;
import com.projects.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        // Encrypt Password before inserting into Database
        String encryptedPsw = Md5Util.getMD5String(password);

        // Insert into Database
        userMapper.add(username, encryptedPsw);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer ID = (Integer) claims.get("ID");
        userMapper.updateAvatar(avatarUrl, ID);
    }

    @Override
    public void updatePsw(String newPsw) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer ID = (Integer) claims.get("ID");
        userMapper.updatePsw(Md5Util.getMD5String(newPsw), ID);
    }
}
