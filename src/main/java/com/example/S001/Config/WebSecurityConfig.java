package com.example.S001.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
//		  http.authorizeRequests()
//          // アクセス権限の設定
//          // staticディレクトリにある、'/css/','fonts','/js/'は制限なし
//          .antMatchers("/css/**", "/fonts/**", "/js/**").permitAll()
//          // '/admin/'で始まるURLには、'ADMIN'ロールのみアクセス可
//          .antMatchers("/admin/**").hasRole("ADMIN")
//          // 他は制限なし
//          .anyRequest().authenticated()
//        .and()
//          // ログイン処理の設定
//          .formLogin()
//            // ログイン処理のURL
//            .loginPage("/login")
            // usernameのパラメタ名
            .usernameParameter("employeeNumber")
//            // passwordのパラメタ名
            .passwordParameter("password")
//            .permitAll()
        .and()
          // ログアウト処理の設定
          .logout()
            // ログアウト処理のURL
//            .logoutRequestMatcher(new AntPathRequestMatcher("/UserTop"))
            // ログアウト成功時の遷移先URL
//            .logoutSuccessUrl("/UserTop")
            // ログアウト時に削除するクッキー名
            .deleteCookies("JSESSIONID")
            // ログアウト時のセッション破棄を有効化
            .invalidateHttpSession(true)
            .permitAll()
        ;
    }

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
