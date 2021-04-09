package com.example.S001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.S001.entity.TrueFalseHistory;

@Mapper
public interface TrueFalseHistoryMapper {

	int insertTrueFalseHistory(@Param("trueFalseHistoryList")List<TrueFalseHistory> trueFalseHistoryList);
}
