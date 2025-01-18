package com.projects.mapper;

import com.projects.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Insert("insert into user(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic=#{avatarUrl}, update_time=now() where id = #{ID}")
    void updateAvatar(String avatarUrl, Integer ID);

    @Update("update user set password=#{newPsw}, update_time=now() where id = #{ID}")
    void updatePsw(String newPsw, Integer ID);
}
