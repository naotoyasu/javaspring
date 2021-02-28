package com.example.S001.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.User;

@Mapper
public interface UserMapper {
	//1件検索
	User findById(User user);

	//全権検索
	List<User> findAll();
}
