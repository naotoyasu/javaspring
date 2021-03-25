package com.example.S001.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.S001.entity.QualificationMaster;
import com.example.S001.mapper.QualificationMapper;


/**
 * 個人ユーザ用サービスクラス
 */
@Service
public class UserService {
    @Autowired
    QualificationMapper qualificationMapper;

    public List<QualificationMaster> getQualification() {
    	List<QualificationMaster> qualificationList = qualificationMapper.getQualificationMaster();
    	return qualificationList;
    }
}
