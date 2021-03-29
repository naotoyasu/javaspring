package com.example.S001.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class CommonHelper {

	public String getUserName() {
		//ログイン中のユーザの取得
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName =null;
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails details = UserDetails.class.cast(authentication.getPrincipal());
			userName = details.getUsername();
		}
		return userName;
	}

}
