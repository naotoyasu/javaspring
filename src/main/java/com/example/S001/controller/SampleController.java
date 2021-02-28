package com.example.S001.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.S001.entity.User;
import com.example.S001.form.UserForm;
import com.example.S001.service.UserService;

@Controller
@RequestMapping("/user")
public class SampleController {

    @Autowired
    UserService userService;


    @RequestMapping("/search")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "ユーザ検索");

        //userform（formクラス）がnullじゃなかったら1件検索
        if(userForm.getId() != null) {
            User user = userService.findById(userForm.getId());
            model.addAttribute("user", user);
        }

        //一覧表示ボタンが押されると本一覧をmodelに登録。
        if(showList != null) {
            List<User> bookList = userService.getUserList();
            model.addAttribute("bookList", bookList);
        }

        return "index";

    }

}
