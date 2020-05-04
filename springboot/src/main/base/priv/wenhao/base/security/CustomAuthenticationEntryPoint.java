package priv.wenhao.base.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import priv.wenhao.base.em.ExceptionEnum;
import priv.wenhao.base.pojo.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * ClassName: CustomAuthenticationEntryPoint
 * Description: 没有登录不在跳转回到登录页,返回json
 * Author: yuWenHao
 * Date: 2020/4/27
 */


@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		httpServletResponse.setContentType("application/json;charset=utf-8");
		PrintWriter printWriter = httpServletResponse.getWriter();
		ResultVo resultVo=new ResultVo();
		resultVo.setCode( ExceptionEnum.NOLOGIN.getCode());
		resultVo.setMessage(ExceptionEnum.NOLOGIN.getMessage());
		printWriter.write(JSON.toJSONString(resultVo));
		printWriter.flush();
		printWriter.close();

	}
}
