package com.example.S001.form;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.example.S001.entity.QualificationMaster;

import lombok.Data;

@Data
public class QuestionForm {
	@NotBlank(message="資格は必須です")
	private String qualification;

	private String varsion;

	@NotBlank(message="設問は必須です")
	private String question;

	@NotBlank(message="正解を一つ選択してください")
	private String answer;

	@NotBlank(message="解答群ａを入力してください")
	private String answerList1;

	@NotBlank(message="解答群ｂを入力してください")
	private String answerList2;

	@NotBlank(message="解答群ｃを入力してください")
	private String answerList3;

	@NotBlank(message="解答群ｄを入力してください")
	private String answerList4;

	private String answerNo;
	private String comment;
	private List<QualificationMaster> qualificationList;
	private String qualificationListJson;
	private Integer qualificationSeqNo;

}
