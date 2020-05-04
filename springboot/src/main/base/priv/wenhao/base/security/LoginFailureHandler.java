package priv.wenhao.base.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import priv.wenhao.base.em.ExceptionEnum;
import priv.wenhao.base.pojo.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: LoginFailureHandler
 * Description: 将登录失败转为json返回给前端
 * Author: yuWenHao
 * Date: 2020/4/27
 */


@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=utf-8");
		PrintWriter out = httpServletResponse.getWriter();
		ResultVo resultVo=new ResultVo();
		if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
			resultVo.setCode(ExceptionEnum.LOGINFAILED.getCode());
			resultVo.setMessage(ExceptionEnum.LOGINFAILED.getMessage());
		} else if (e instanceof DisabledException) {
			resultVo.setCode(ExceptionEnum.USERENABLE.getCode());
			resultVo.setMessage(ExceptionEnum.USERENABLE.getMessage());
		} else {
			resultVo.setCode(3);
			resultVo.setMessage("登录失败");
		}
		out.write(JSON.toJSONString(resultVo));
		out.flush();
		out.close();
	}
}
