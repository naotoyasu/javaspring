package com.example.S001.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.entity.Account;
import com.example.S001.entity.DepartmentMaster;
import com.example.S001.form.UserForm;
import com.example.S001.service.AdminUserService;

/**
 * ユーザ管理画面コントローラ
 */
@Controller
public class AdminUserConrtoller {

	@Autowired
    AdminUserService adminUserService;


	/**
	 * 初期画面表示用
	 * @param userForm 入出力用ユーザフォーム
	 * @param showList
	 * @param model モデル
	 * @retur 表示画面（AdminUserTop）
	 */
	@RequestMapping("/AdminUser/Top")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "ユーザ管理画面");

        return "AdminUserTop";

    }



    /**
     * 検索ボタン押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（AdminUserTop）
     */
    @RequestMapping(value = "/AdminUser/Top", params = "userSerch", method = RequestMethod.POST)
    public String userSerch (UserForm userForm, Model model) {

    	//userform（formクラス）がnullじゃなかったら1件検索
        if(!userForm.getEmployeeNumber().isEmpty()) {
            Account account = adminUserService.findByEmployeeNumber(userForm.getEmployeeNumber());
            model.addAttribute("account", account);
        }else {
        	//nullなら全件検索
        	List<Account> accountList = adminUserService.getAcountList();
            model.addAttribute("accountList", accountList);

        }


    	return "AdminUserTop";

    }

    /**
     * 新規登録ボタン押下時
     * @param userForm ユーザフォーム
     * @param model モデル
     * @return 表示画面（UserAdd）
     */
    @RequestMapping(value = "/AdminUser/Top", params = "userAdd", method = RequestMethod.POST)
    public String userAdd (UserForm userForm, Model model) {

    	//部署名リストを取得
        model.addAttribute("title", "ユーザ新規登録");
    	List<DepartmentMaster> departmentList = adminUserService.getDepartment();


    	userForm.setDepartmentList(departmentList);
    	model.addAttribute("userForm", userForm);

    	//エラー判定メッセージ
    	List<String> errMsg = new ArrayList<String>();
    	errMsg = null;
    	model.addAttribute("msg", errMsg);

    	return "userAdd";

    }


    /**
     * 登録実行ボタン押下時
     * @param userForm ユーザフォーム
     * @param model モデル
     * @return 表示画面（Top画面）
     */
    @RequestMapping(value = "/AdminUser/Top", params = "userAddSub", method = RequestMethod.POST)
    public String userAddSub (@Valid UserForm userForm, BindingResult result,Model model) {

    	if(result.hasErrors()) {
    		return "userAdd";
    	}
    	//エラー判定メッセージ
    	List<String> errMsg = new ArrayList<String>();

    	//チェック
    	errMsg = adminUserService.userCheck(userForm);

    	//パスワードのハッシュ化
    	userForm.setPassword(adminUserService.passwordHash(userForm.getPassword()));

    	//エラーがなければ登録
    	if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
    		adminUserService.userInsert(userForm);
    	}

    	//エラーがあった場合は登録画面に返す
    	if (!errMsg.isEmpty()) {
        	//部署名リストを取得
            model.addAttribute("title", "ユーザ新規登録");
        	model.addAttribute("userForm", userForm);
        	model.addAttribute("msg", errMsg);

        	return "userAdd";

    	}
    	return "redirect:/AdminUser/Top";

    }

}
