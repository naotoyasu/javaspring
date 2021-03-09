package com.example.S001.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.S001.entity.Department;

@Mapper
public interface DepartmentMapper {
	List<Department> getDepartment();

}
