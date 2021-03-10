package com.example.S001.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.S001.entity.Account;

@Service
public class UserDetailsServiceImple implements UserDetailsService {
	@Autowired
	AdminUserService adminUserServie;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = adminUserServie.findByEmployeeNumber(username);
		return new UserDetailsImpl(account);
	}

}
