package com.example.S001.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.entity.QuestionCollection;
import com.example.S001.entity.TrueFalseHistory;
import com.example.S001.form.LearningTopForm;
import com.example.S001.form.QuestionForm;
import com.example.S001.mapper.QualificationMapper;
import com.example.S001.mapper.QuestionCollectionMapper;
import com.example.S001.mapper.TrueFalseHistoryMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 個人ユーザ用サービスクラス
 */
/**
 * @author toot
 *
 */
/**
 * @author toot
 *
 */
/**
 * @author toot
 *
 */
/**
 * @author toot
 *
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

    @Autowired
    TrueFalseHistoryMapper trueFalseHistoryMapper;

    /**
     * 資格マスタ取得
     * @return 資格マスタ
     */
    public List<QualificationMaster> getQualification() {
    	List<QualificationMaster> qualificationList = qualificationMapper.getQualificationMaster();
    	return qualificationList;
    }


    /**
     * 問題登録
     * @param form 問題
     */
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

    /**
     * 問題取得メソッド
     * @param form 学習内容選択フォーム
     * @return 問題集（リスト）
     */
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

    	//とりあえず5で固定
    	int maxList =5;

    	//取得した問題をシャッフル
    	 Collections.shuffle(list);

    	 List<QuestionCollection> sbList;
    	 if (list.size() >=maxList) {
    		 sbList = list.subList(0, maxList);
    	 }else {
    		 sbList = list;
    	 }

    	return sbList;

    }


    /**
     * 資格名とバージョンから資格シーケンスNoの取得
     * @param selectQualification　資格名
     * @param selectVertion　バージョン
     * @return　資格シーケンスNo
     */
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

    /**
     * Json変換
     * @param object
     * @return
     */
    public String getJson(Object object) {

    	ObjectMapper mapper = new ObjectMapper();
    	String JsonString = null;
    	try {
    		JsonString = mapper.writeValueAsString(object);
    	}catch(Exception e){
    		e.printStackTrace();
    	}

    	return JsonString;
    }

   public void setHistory(String json) {

	   //JSONからJavaオブジェクトに変換
	   ObjectMapper mapper = new ObjectMapper();
	   	List<QuestionCollection> qcList = null;
	   	try {
	   		qcList = mapper.readValue(json,new TypeReference<List<QuestionCollection>>() {});
	   	}catch(Exception e){
	   		e.printStackTrace();
	   	}


    	//共通ヘルパーを使用して、ログイン中のユーザ名を取得
        CommonHelper commonHelper = new CommonHelper();

    	String employeeNumber = commonHelper.getUserName();

	   	//インサート用List作成
	   	List<TrueFalseHistory> historyList = new ArrayList<TrueFalseHistory>();

	   	String swithStar;

	   	//解答があっているか確認
   		for (QuestionCollection qc: qcList) {
   			TrueFalseHistory trueFalseHistory = new TrueFalseHistory();

   			//ユーザ
   			trueFalseHistory.setEmployeeNumber(employeeNumber);

   			//問題SEQ_NO
   			trueFalseHistory.setQcSeqNo(qc.getQcSeqNo());

   			swithStar=qc.getStar();
   			if(swithStar == null) {
   				swithStar ="－";
   			}
   			if(qc.getAnswerNo().equals(qc.getChooseAnswer())) {
   				//正解　★を設定
   				switch(swithStar) {
   					case oneStar:
   						trueFalseHistory.setStar(twoStar);
   						break;
   					case twoStar:
   					case threeStar:
   						trueFalseHistory.setStar(threeStar);
   						break;
   					default:
   						trueFalseHistory.setStar(oneStar);
   				}
   			}else {
   				//不正解　★を設定
   				switch(swithStar) {
					case oneStar:
						trueFalseHistory.setStar("-");
						break;
					case twoStar:
						trueFalseHistory.setStar(oneStar);
						break;
					case threeStar:
						trueFalseHistory.setStar(twoStar);
						break;
					default:
						trueFalseHistory.setStar("-");
				}

   			}

   			//履歴リストに追加
   			historyList.add(trueFalseHistory);
   		}

   		//インサート発行
   		trueFalseHistoryMapper.insertTrueFalseHistory(historyList);




   }
}
