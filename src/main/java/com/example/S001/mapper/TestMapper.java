package com.example.S001.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.Test;

@Mapper
public interface TestMapper {
	//全権検索
	List<Test> getTest();


}
