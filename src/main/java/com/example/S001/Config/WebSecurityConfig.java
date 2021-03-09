package com.example.S001.Config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		/* */.mvcMatchers("/UserAdmin/Top").permitAll()
        .and()
        // LOGIN
        .formLogin()
        /* */.defaultSuccessUrl("/UserAdmin/Top")
    // end
    ;
	}

}
