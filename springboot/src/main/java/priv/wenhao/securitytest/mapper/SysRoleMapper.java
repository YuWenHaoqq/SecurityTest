package priv.wenhao.securitytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import priv.wenhao.base.pojo.dto.SysRoleDto;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleDto> {
	/***
	* ClassName:SysRoleMapper
	* Description: 通过教师id获得角色
	* param:[id]
	* return:java.util.List<java.lang.String>
	* Author:yu wenhao
	* date:2020/4/23
	*/
	public List<String> getRoleNameById(String id);
}
