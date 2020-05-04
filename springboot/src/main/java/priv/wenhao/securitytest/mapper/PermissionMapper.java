package priv.wenhao.securitytest.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
/**
 * ClassName: PermissionMapper
 * Description: 权限mapper
 * Author: yuWenHao
 * Date: 2020/4/29
 */

@Mapper
public interface PermissionMapper {
	/***
	* ClassName:PermissionMapper
	* Description: 用过路径获得权限
	* param:[menu]
	* return:java.util.List<java.lang.String>
	* Author:yu wenhao
	* date:2020/4/29
	*/
	public List<String> getPermissionByMenu(String menu);
}
