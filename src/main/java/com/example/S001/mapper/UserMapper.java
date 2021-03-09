package com.example.S001.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.User;
import com.example.S001.form.UserForm;

@Mapper
public interface UserMapper {
	//1件検索
	User findByEmployeeNumber(Integer employeeNumber);

	//全権検索
	List<User> findAll();

	//登録
	int userInsert(UserForm userForm);

}
