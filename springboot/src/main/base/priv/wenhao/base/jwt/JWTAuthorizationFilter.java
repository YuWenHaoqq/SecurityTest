package priv.wenhao.base.jwt;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import priv.wenhao.base.util.JwtTokenUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//		super.doFilterInternal(request, response, chain);
		String tokenHeader=request.getHeader(JwtTokenUtils.TOKEN_HEADER);
//		如果请求头中没有Authorization直接放行
		if (tokenHeader==null||tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)){
			chain.doFilter(request,response);
			return;
		}
//		如果请求头中有token则进行解析,并且设置认证信息
		SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
		super.doFilterInternal(request,response,chain);
	}

//	这里从token中获取用户信息并创建一个token
	private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){
		String token=tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX,"");
		String username=JwtTokenUtils.getUserName(token);
		if (username!=null){
			return new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>());
		}

		return null;
	}
}
