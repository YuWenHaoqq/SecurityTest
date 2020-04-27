package priv.wenhao.base.config.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import priv.wenhao.base.em.ExceptionEnum;
import priv.wenhao.base.pojo.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: AuthenticationAccessDeniedHandler
 * Description: 访问地址没有权限返回json字符串
 * Author: yuWenHao
 * Date: 2020/4/27
 */


@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
		resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		ResultVo resultVo=new ResultVo();
		resultVo.setCode(ExceptionEnum.NOPERMISSIONS.getCode());
		resultVo.setMessage(ExceptionEnum.NOPERMISSIONS.getMessage());
		out.write(JSON.toJSONString(resultVo));
		out.flush();
		out.close();
	}
}
