package com.example.S001.form;
import java.util.List;

import com.example.S001.entity.Department;

import lombok.Data;

@Data
public class UserForm {
	private String employeeNumber;
	private String name;
	private Integer departmentId;
	private String departmentName;
	private String password;
	private String passwordChk;
	private List<Department> departmentList;

}
