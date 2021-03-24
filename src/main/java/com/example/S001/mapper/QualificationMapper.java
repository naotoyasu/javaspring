package com.example.S001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.QualificationMaster;

@Mapper
public interface QualificationMapper {
	List<QualificationMaster> getQualificationMaster();

}
