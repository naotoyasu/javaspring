package com.example.S001.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.entity.Account;
import com.example.S001.form.UserForm;
import com.example.S001.service.UserService;

/**
 * 個人ユーザ画面コントローラ
 */
@Controller
public class UserController {
	@Autowired
    UserService userService;


	@RequestMapping("/UserTop")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "ユーザ画面");

        return "userTop";

    }

    /**
     * 問題登録ボタン押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（AdminUserTop）
     */
    @RequestMapping(value = "/AdminUser/Top", params = "userSerch", method = RequestMethod.POST)
    public String userSerch (UserForm userForm, Model model) {

    	//userform（formクラス）がnullじゃなかったら1件検索
        if(!userForm.getEmployeeNumber().isEmpty()) {
            Account account = userService.findByEmployeeNumber(userForm.getEmployeeNumber());
            model.addAttribute("account", account);
        }else {
        	//nullなら全件検索
        	List<Account> accountList = userService.getAcountList();
            model.addAttribute("accountList", accountList);

        }


    	return "AdminUserTop";

    }

}
