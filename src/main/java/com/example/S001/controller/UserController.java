package com.example.S001.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.form.LearningTopForm;
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

    	return "redirect:/AddQuestion";

    }

	@RequestMapping("/AddQuestion")
    public String addQuestion (QuestionForm form, Model model) {

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

    	//エラー判定メッセージ
    	List<String> errMsg = new ArrayList<String>();
    	errMsg = null;
    	model.addAttribute("msg", errMsg);

    	return "/AddQuestion";

    }

    /**
     * 登録ボタン押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（AdminUserTop）
     */
	@RequestMapping(value = "/AddQuestion", params = "entry", method = RequestMethod.POST)
    public String entry (@Valid @ModelAttribute("form") QuestionForm form, BindingResult result,Model model) {


    	if(result.hasErrors()) {
    		return "/AddQuestion";
    	}

    	//ユーザフォームに入力された内容でDB登録
    	userService.entryQualification(form);

    	return "redirect:/AddQuestion";

    }

    /**
     * 学習ボタン押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（AdminUserTop）
     */
    @RequestMapping(value = "/UserTop", params = "learning", method = RequestMethod.POST)
    public String learning () {

    	return "redirect:/learningTop";

    }

	@RequestMapping("/learningTop")
    public String learningTop (LearningTopForm form, Model model) {

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

    	return "/learningTop";

    }

    /**
     * 学習開始押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（AdminUserTop）
     */
	@RequestMapping(value = "/learningTop", params = "learningStart", method = RequestMethod.POST)
    public String learningStart (@Valid @ModelAttribute("form") LearningTopForm form, BindingResult result,Model model) {


    	if(result.hasErrors()) {
    		return "/learningTop";
    	}

    	//選択した内容で問題取得
    	//(ここでは10問ずつとする)
    	userService.getQuestionCollection(form);

    	return "redirect:/AddQuestion";

    }
}
