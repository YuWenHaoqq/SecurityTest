package priv.wenhao.base.config.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import priv.wenhao.base.pojo.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=utf-8");
		PrintWriter out = httpServletResponse.getWriter();
		ResultVo resultVo=new ResultVo();
		resultVo.setCode(0);
		resultVo.setMessage("登录成功");
		String s = JSON.toJSONString(resultVo);
		out.write(s);
		out.flush();
		out.close();
	}
}
