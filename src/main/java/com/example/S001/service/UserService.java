package com.example.S001.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.entity.QuestionCollection;
import com.example.S001.form.LearningTopForm;
import com.example.S001.form.QuestionForm;
import com.example.S001.mapper.QualificationMapper;
import com.example.S001.mapper.QuestionCollectionMapper;

/**
 * 個人ユーザ用サービスクラス
 */
@Service
public class UserService {

	final String oneStar ="★";
	final String twoStar ="★★";
	final String threeStar ="★★★";
	final String allStar ="ALL";




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

    	//資格とversionから資格シーケンスNoを取得
    	Integer qualificatioSeqNo=getQualificationSeqNo(form.getQualification(),form.getVarsion());
    	form.setQualificationSeqNo(qualificatioSeqNo);

    	if(qualificatioSeqNo==0) {
    		System.out.println("資格シーケンスNoが取得できません");
    		System.exit(1);
    	}

    	questionCollectionMapper.insertQuestionCollection(form);


    }

    public List<QuestionCollection> getQuestionCollection(LearningTopForm form) {

    	//共通ヘルパーを使用して、ログイン中のユーザ名を取得
        CommonHelper commonHelper = new CommonHelper();

    	String userName = commonHelper.getUserName();
    	form.setEmployeeNumber(userName);

    	//資格とversionから資格シーケンスNoを取得
    	Integer qualificatioSeqNo=getQualificationSeqNo(form.getQualification(),form.getVarsion());
    	form.setQualificationSeqNo(qualificatioSeqNo);

    	//履歴からの取得
    	List<QuestionCollection> list = null;
    	switch(form.getSelectStar()) {
    		//★
    		case oneStar:
    			//以外検索
    			list = questionCollectionMapper.selectStarOne(form);
    			break;
    		case twoStar:
    		case threeStar:
    			//イコール検索
    			list = questionCollectionMapper.selectStar(form);
    			break;
    		case allStar:
    			//条件なし
    			list = questionCollectionMapper.selectStarAll(form);
    			break;
    	}

    	//とりあえず10で固定
    	int maxList =5;

    	 Collections.shuffle(list);

    	 List<QuestionCollection> sbList;
    	 if (list.size() >=maxList) {
    		 sbList = list.subList(0, maxList);
    	 }else {
    		 sbList = list;
    	 }

    	return sbList;

    }
	private Integer getQualificationSeqNo(String selectQualification,String selectVertion ) {

    	//資格とversionから資格シーケンスNoを判定
    	Integer qualificatioSeqNo=0;

    	List<QualificationMaster> qualificationList = qualificationMapper.getQualificationMaster();

    	for (QualificationMaster qualification: qualificationList){
    		//まずは資格名があっているか
    		if(selectQualification.equals(qualification.getQualification()))
    			//varsionがあっているか
    			//versionが「---」の場合はNullと比較
    			if(selectVertion.equals("---")){
    				if(qualification.getVarsion()==null) {
    					qualificatioSeqNo = qualification.getQualificatioSeqNo();
    				}

    			}else{
    				if(selectVertion.equals(qualification.getVarsion())) {
    					qualificatioSeqNo = qualification.getQualificatioSeqNo();
    				}
    			}
    		}
    	return qualificatioSeqNo;

	}


}
