package com.example.S001.form;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.S001.entity.QualificationMaster;

import lombok.Data;

@Data
public class LearningTopForm {
	@NotBlank(message="資格は必須です")
	private String qualification;

	private String varsion;

	private String selectStar;

	private String employeeNumber;
	private Integer qualificationSeqNo;
	private List<QualificationMaster> qualificationList;
	private String qualificationListJson;

}
