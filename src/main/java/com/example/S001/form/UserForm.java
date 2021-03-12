package com.example.S001.form;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.S001.entity.DepartmentMaster;

import lombok.Data;

@Data
public class UserForm {
	@NotBlank(message="社員番号は必須です")
	@Size(min=7,message="社員番号は7桁以上で入力してください")
	private String employeeNumber;
	@NotBlank(message="名前は必須です")
	private String name;
	private Integer departmentId;
	private String departmentName;
	@NotBlank(message="パスワードは必須です")
	@Size(min=8,message="パスワードは8桁以上で入力してください")
	private String password;
	private String passwordChk;
	private List<DepartmentMaster> departmentList;

}
