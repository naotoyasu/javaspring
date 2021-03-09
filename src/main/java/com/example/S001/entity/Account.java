package com.example.S001.entity;

import lombok.Data;

@Data
public class Account {
    private String employeeNumber;
    private Integer departmentId;
    private String name;
    private String role;
    private String password;
}
