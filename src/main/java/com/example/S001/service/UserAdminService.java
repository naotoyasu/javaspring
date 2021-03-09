package com.example.S001.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.example.S001.entity.Department;
import com.example.S001.entity.Account;
import com.example.S001.form.UserForm;
import com.example.S001.mapper.DepartmentMapper;
import com.example.S001.mapper.AccountMapper;

@Service
public class UserAdminService {
	@Autowired
	AccountMapper userMapper;

	@Autowired
	DepartmentMapper departmentMapper;

	public Account findByEmployeeNumber(String employeeNumber) {
		Account user = userMapper.findByEmployeeNumber(employeeNumber);
		return  user;
	}

    //全件取得
    public List<Account> getUserList(){
        return this.userMapper.findAll();
    }

    //部署名リストの取得

    public List<Department> getDepartment(){
    	List<Department> departmentList = departmentMapper.getDepartment();
    	return departmentList;
    }


    /**
     * 入力内容のチェック
     * @param userForm 画面にて入力されたユーザフォーム
     * @return エラーメッセージ
     */
    public List userCheck(UserForm userForm){
    	List<String> msg = new ArrayList<String>();


    	//パスワードと確認用パスワードがあっているか確認
    	if(!userForm.getPassword().equals(userForm.getPasswordChk())) {
    		msg .add("確認用パスワードが一致しません");
    	}

    	//社員番号が登録済みかチェック
    	Account user = findByEmployeeNumber(userForm.getEmployeeNumber());
    	if (!Objects.isNull(user)) {
    		msg .add("既に登録済みです");
    	}

    	return msg;
    }

    public void userInsert(UserForm userForm){

    	//登録処理
    	int count = userMapper.userInsert(userForm);
    }

    public String passwordHash(String password) {
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
    	return  hashed;
    }


}
