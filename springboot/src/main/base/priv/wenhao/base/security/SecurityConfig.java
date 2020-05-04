package priv.wenhao.base.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import priv.wenhao.base.jwt.JWTAuthenticationFilter;
import priv.wenhao.base.jwt.JWTAuthorizationFilter;


/**
 * ClassName: SecurityConfig
 * Description: 配置security
 * Author: yuWenHao
 * Date: 2020/4/27
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	private LoginFailureHandler loginFailureHandler;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private UrlAccessDecisionManager accessDecisionManager;
	@Autowired
	private UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;


	@Bean
	public PasswordEncoder passwordEncoder() {
//		不使用加密方法
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
//		super.configure(web);
//		放行swagger,注意注掉
		web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
				"/swagger-resources", "/swagger-resources/configuration/security",
				"/swagger-ui.html", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
//				关闭csrf
				.csrf().disable()
				.authorizeRequests()
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
						o.setAccessDecisionManager(accessDecisionManager);
						return o;
					}
				})
//				设置权限
//				.antMatchers("/success").hasAuthority("p1")
//				.antMatchers("/tt").hasAuthority("p2")
//				放行swagger,记得关闭

				.anyRequest().authenticated()
				.and()
//				允许表单登入
				.formLogin()
//				.loginPage("/login")
				.loginProcessingUrl("/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
//				登入成功自动跳转
//				.defaultSuccessUrl("/swagger-ui.html", true)
//				捕获登录失败
				.failureHandler(loginFailureHandler)
//				捕获登录成功
				.successHandler(loginSuccessHandler)
				.and()
//				捕获异常
				.exceptionHandling()
				.accessDeniedHandler(authenticationAccessDeniedHandler)
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
//				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
//				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
//				登出请求/logout
				.logout().permitAll()
				.and()
				.httpBasic();
	}

}
