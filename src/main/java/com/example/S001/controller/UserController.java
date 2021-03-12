package com.example.S001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.S001.form.UserForm;

/**
 * 個人ユーザ画面コントローラ
 */
@Controller
public class UserController {
	@RequestMapping("/UserTop")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "ユーザ画面");

        return "userTop";

    }

}
