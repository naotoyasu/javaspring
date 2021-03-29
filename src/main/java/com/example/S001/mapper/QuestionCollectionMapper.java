package com.example.S001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.QuestionCollection;
import com.example.S001.form.LearningTopForm;
import com.example.S001.form.QuestionForm;

@Mapper
public interface QuestionCollectionMapper {
	void insertQuestionCollection(QuestionForm form);

	List<QuestionCollection> selectStarOne(LearningTopForm form);


	List<QuestionCollection> selectStarAll(LearningTopForm form);

	List<QuestionCollection> selectStar(LearningTopForm form);

}
