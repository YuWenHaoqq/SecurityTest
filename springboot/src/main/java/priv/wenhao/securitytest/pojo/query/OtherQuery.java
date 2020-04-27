package priv.wenhao.securitytest.pojo.query;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class OtherQuery {
	@NotNull(message = "请输入kk")
	@Size(min = 6,max = 16,message = "请输入6-16个字符")
	private String kk;
}
