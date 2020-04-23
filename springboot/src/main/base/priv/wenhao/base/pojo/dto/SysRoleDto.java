package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("sys_role")
public class SysRoleDto {
	@TableId("id")
	@ApiModelProperty("角色表自增id")
	private Integer id;
	@TableField("role_name")
	@ApiModelProperty("角色名字")
	private String roleName;
}
