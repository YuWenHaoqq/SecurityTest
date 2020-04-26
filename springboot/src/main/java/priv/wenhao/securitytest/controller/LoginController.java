package priv.wenhao.securitytest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.em.ExceptionEnum;
import priv.wenhao.base.pojo.vo.ResultVo;

@Api(tags = "登录测试接口")
@RestController
public class LoginController {
	@GetMapping("/success")
	public ResultVo loginSuccess(){
		ResultVo resultVo=new ResultVo();
		resultVo.setMessage("登录成功");
		return resultVo;
	}

	@GetMapping("/tt")
	public ResultVo test(){
		ResultVo resultVo=new ResultVo();
		resultVo.setMessage("ccc");
		return resultVo;
	}
}
