package com.example.S001.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.form.QuestionForm;

@Mapper
public interface QuestionCollectionMapper {
	void insertQuestionCollection(QuestionForm form);

}
