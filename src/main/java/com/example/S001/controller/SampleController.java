package com.example.S001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.form.UserForm;

@Controller
public class SampleController {

//    @Autowired
//    TestService testService;


    @RequestMapping("/test")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "テスト2");
        return "redirect:test2";

    }

    @RequestMapping("/test2")
    public String test2(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "テスト3");
        return "test2";

    }

    //POSTメソッド
    @RequestMapping(value = "/test",  method = RequestMethod.POST)
    public String testPost (UserForm userForm, Model model) {

    	return "test";

    }

    //Getメソッド
//    @RequestMapping(value = "/test",  method = RequestMethod.GET)
//    public String userSerch (UserForm userForm, Model model) {
//
//    	return "test";
//
//    }

}
