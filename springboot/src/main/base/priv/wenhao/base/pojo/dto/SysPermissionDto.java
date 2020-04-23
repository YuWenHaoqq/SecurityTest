package priv.wenhao.base.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_power")
public class SysPermissionDto {
	@TableId("id")
	private Integer id;
	@TableField("power_type")
	private String powerType;
	@TableField("url")
	private String url;
}