package priv.wenhao.base.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import priv.wenhao.base.pojo.dto.AccountDto;
import priv.wenhao.base.pojo.query.LoginQuery;
import priv.wenhao.base.util.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//@Component
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

//	从输出流中获取登录信息
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//		return super.attemptAuthentication(request, response);
		try{
			LoginQuery loginUser=new ObjectMapper().readValue(request.getInputStream(),LoginQuery.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword(),new ArrayList<>())
			);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

//	成功验证后调用的方法
//	如果验证成功,就生成token并返回
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//		super.successfulAuthentication(request, response, chain, authResult);
		AccountDto accountDto= (AccountDto) authResult.getPrincipal();
		System.out.println("jwtUser:"+accountDto.toString());
		String token= JwtTokenUtils.createToken(accountDto.getUsername(),false);
//		返回创建成功的token
//		但是这里创建的token只是单纯的token
//		按照jwt规定,最够的请求格式Bearer token
		response.setHeader("token",JwtTokenUtils.TOKEN_PREFIX+token);

	}

//	验证失败后端调用的方法
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//		super.unsuccessfulAuthentication(request, response, failed);
		response.getWriter().write("authentication failed, reason: " + failed.getMessage());
	}
}
