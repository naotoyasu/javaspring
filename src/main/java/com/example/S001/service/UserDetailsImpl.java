package com.example.S001.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.example.S001.entity.Account;

public class UserDetailsImpl extends User {
    private static final long serialVersionUID = 1L;

    private final Account account;
	  public UserDetailsImpl(Account account) {
	        super(account.getEmployeeNumber(), account.getPassword(), AuthorityUtils
	                .createAuthorityList("ROLE_USER"));
	        this.account = account;
	    }

	    public Account getAccount() {
	        return account;
	    }


}
