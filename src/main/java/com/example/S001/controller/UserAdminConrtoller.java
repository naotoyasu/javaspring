package com.example.S001.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.S001.entity.Department;
import com.example.S001.entity.Account;
import com.example.S001.form.UserForm;
import com.example.S001.service.UserAdminService;

/**
 * ユーザ管理画面コントローラ
 */
@Controller
public class UserAdminConrtoller {

	@Autowired
    UserAdminService userAdminService;


	/**
	 * 初期画面表示用
	 * @param userForm 入出力用ユーザフォーム
	 * @param showList
	 * @param model モデル
	 * @retur 表示画面（userAdminTop）
	 */
	@RequestMapping("/userAdmin/Top")
    public String index(UserForm userForm, String showList, Model model) {

        //タイトル
        model.addAttribute("title", "ユーザ管理画面");

        return "userAdminTop";

    }



    /**
     * 検索ボタン押下時
     * @param userForm 入出力用フォーム
     * @param model モデル
     * @return 表示画面（userAdminTop）
     */
    @RequestMapping(value = "/userAdmin/Top", params = "userSerch", method = RequestMethod.POST)
    public String userSerch (UserForm userForm, Model model) {

    	//userform（formクラス）がnullじゃなかったら1件検索
        if(userForm.getEmployeeNumber() != null) {
            Account user = userAdminService.findByEmployeeNumber(userForm.getEmployeeNumber());
            model.addAttribute("user", user);
        }


    	return "userAdminTop";

    }

    /**
     * 新規登録ボタン押下時
     * @param userForm ユーザフォーム
     * @param model モデル
     * @return 表示画面（UserAdd）
     */
    @RequestMapping(value = "/userAdmin/Top", params = "userAdd", method = RequestMethod.POST)
    public String userAdd (UserForm userForm, Model model) {

    	//部署名リストを取得
        model.addAttribute("title", "ユーザ新規登録");
    	List<Department> departmentList = userAdminService.getDepartment();


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
    @RequestMapping(value = "/userAdmin/Top", params = "userAddSub", method = RequestMethod.POST)
    public String userAddSub (UserForm userForm, Model model) {

    	//エラー判定メッセージ
    	List<String> errMsg = new ArrayList<String>();

    	//チェック
    	errMsg = userAdminService.userCheck(userForm);

    	//パスワードのハッシュ化
    	userForm.setPassword(userAdminService.passwordHash(userForm.getPassword()));

    	//エラーがなければ登録
    	if (Objects.isNull(errMsg) || errMsg.isEmpty()) {
        	userAdminService.userInsert(userForm);
    	}

    	//エラーがあった場合は登録画面に返す
    	if (!errMsg.isEmpty()) {
        	//部署名リストを取得
            model.addAttribute("title", "ユーザ新規登録");
        	model.addAttribute("userForm", userForm);
        	model.addAttribute("msg", errMsg);

        	return "userAdd";

    	}
    	return "redirect:/userAdmin/Top";

    }

}
