package priv.wenhao.securitytest.mapper;

import org.apache.ibatis.annotations.Mapper;
import priv.wenhao.base.pojo.dto.SysPermissionDto;

import java.util.List;

@Mapper
public interface SysPermissionMapper {
	/***
	 * ClassName:SysRoleMapper
	 * Description: 获得对应的路径所需要的权限
	 * param:[]
	 * return:java.util.List<priv.wenhao.dormitory.pojo.dto.RolePermissionDto>
	 * Author:yu wenhao
	 * date:2020/4/21
	 */
	public List<SysPermissionDto> getPermissionByUrl(String url);
}
