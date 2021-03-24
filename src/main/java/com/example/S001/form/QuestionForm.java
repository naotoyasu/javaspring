package com.example.S001.form;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.S001.entity.QualificationMaster;

import lombok.Data;

@Data
public class QuestionForm {
	@NotBlank(message="社員番号は必須です")
	@Size(min=7,message="社員番号は7桁以上で入力してください")
	private String qualification;
	private String varsion;
	private String question;
	private String answerList1;
	private String answerList2;
	private String answerList3;
	private String answerList4;
	private String answer_no;
	private String comment;
	private List<QualificationMaster> qualificationList;
	private String qualificationListJson;

}
