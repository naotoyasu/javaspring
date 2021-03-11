package com.example.S001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.mapper.AccountMapper;


/**
 * 個人ユーザ用サービスクラス
 */
@Service
public class UserService {
    @Autowired
    AccountMapper userMapper;

}
