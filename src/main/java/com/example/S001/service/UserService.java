package com.example.S001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.form.QuestionForm;
import com.example.S001.mapper.QualificationMapper;
import com.example.S001.mapper.QuestionCollectionMapper;


/**
 * 個人ユーザ用サービスクラス
 */
@Service
public class UserService {
    @Autowired
    QualificationMapper qualificationMapper;

    @Autowired
    QuestionCollectionMapper questionCollectionMapper;

    public List<QualificationMaster> getQualification() {
    	List<QualificationMaster> qualificationList = qualificationMapper.getQualificationMaster();
    	return qualificationList;
    }

    public void entryQualification(QuestionForm form) {
    	//正解を設定
    	form.setAnswerNo(form.getAnswer());

    	//資格とversionから資格シーケンスNoを判定
    	Integer qualificatioSeqNo=0;

    	List<QualificationMaster> qualificationList = qualificationMapper.getQualificationMaster();

    	for (QualificationMaster qualification: qualificationList){
    		//まずは資格名があっているか
    		if(form.getQualification().equals(qualification.getQualification()))
    			//varsionがあっているか
    			//versionが「---」の場合はNullと比較
    			if(form.getVarsion().equals("---")){
    				if(qualification.getVarsion()==null) {
    					qualificatioSeqNo = qualification.getQualificatioSeqNo();
    					form.setQualificationSeqNo(qualificatioSeqNo);
    				}

    			}else{
    				if(form.getVarsion().equals(qualification.getVarsion())) {
    					qualificatioSeqNo = qualification.getQualificatioSeqNo();
    					form.setQualificationSeqNo(qualificatioSeqNo);
    				}
    			}
    		}

    	if(qualificatioSeqNo==0) {
    		System.out.println("資格シーケンスNoが取得できません");
    		System.exit(1);
    	}

    	questionCollectionMapper.insertQuestionCollection(form);


    }

}
