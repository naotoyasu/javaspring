package com.example.S001.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.Account;
import com.example.S001.form.UserForm;

@Mapper
public interface AccountMapper {
	//1件検索
	Account findByEmployeeNumber(String employeeNumber);

	//全権検索
	List<Account> findAll();

	//登録
	int userInsert(UserForm userForm);

}
