//package priv.wenhao.base.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.authentication.ForwardAuthenticationSuccessHandler;
//
//
///**
// * ClassName: WebSecurityConfig
// * Description: 安全配置的内容包括:用户信息,密码编辑器,安全拦截机制
// * Author: yuWenHao
// * Date: 2020/4/25
// */
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	//	安全拦截机制(最重要的)
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
////				.antMatchers("/r/**").authenticated()
//				.anyRequest().permitAll()
//				.and()
//				.formLogin()
//				.successForwardUrl("/success")
//		.failureForwardUrl("/fair");
//	}
//
////	密码编码器
//	@Bean
//	public PasswordEncoder passwordEncoder(){
////		用原本的密码进行比较
//		return NoOpPasswordEncoder.getInstance();
//	}
//
//}
