package com.example.S001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.entity.User;
import com.example.S001.mapper.UserMapper;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    //1件検索
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        return this.userMapper.findById(user);
    }

    //全件取得
    public List<User> getUserList(){
        return this.userMapper.findAll();
    }
}
