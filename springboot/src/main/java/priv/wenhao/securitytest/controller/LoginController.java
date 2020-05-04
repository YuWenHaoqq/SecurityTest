package priv.wenhao.securitytest.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.wenhao.base.pojo.vo.ResultVo;
import priv.wenhao.securitytest.pojo.query.LoginQuery;
import priv.wenhao.securitytest.pojo.query.OtherQuery;

import javax.validation.Valid;

@Api(tags = "登录测试接口")
@RestController
public class LoginController {
	@GetMapping("/success")
	public ResultVo loginSuccess(@Valid @ModelAttribute OtherQuery otherQuery){
		ResultVo resultVo=new ResultVo();
		resultVo.setMessage(otherQuery.getKk());
		return resultVo;
	}

	@GetMapping("/tt")
	public ResultVo test(){
		ResultVo resultVo=new ResultVo();
		resultVo.setMessage("ccc");
		return resultVo;
	}
	@PostMapping("/login")
	public String login(@ModelAttribute LoginQuery loginQuery){
		return "777";
	}
}
