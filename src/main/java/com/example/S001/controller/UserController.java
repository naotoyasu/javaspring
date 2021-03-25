package com.example.S001.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.form.QuestionForm;
import com.example.S001.form.UserForm;
import com.example.S001.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    @RequestMapping(value = "/UserTop", params = "addQuestion", method = RequestMethod.POST)
    public String userSerch (QuestionForm form, Model model) {

    	//資格情報の取得
    	List<QualificationMaster> qualificationList = userService.getQualification();
    	ObjectMapper mapper = new ObjectMapper();
    	String qualificationListJson = null;
    	try {
    	qualificationListJson = mapper.writeValueAsString(qualificationList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	form.setQualificationList(qualificationList);
    	form.setQualificationListJson(qualificationListJson);
    	model.addAttribute("form", form);

    	return "AddQuestion";

    }

}
