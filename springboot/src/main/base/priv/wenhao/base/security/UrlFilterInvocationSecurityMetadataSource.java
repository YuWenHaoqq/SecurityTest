package priv.wenhao.base.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import priv.wenhao.securitytest.mapper.PermissionMapper;

import java.util.Collection;
import java.util.List;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private PermissionMapper permissionMapper;
	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		String url=((FilterInvocation)o).getRequestUrl();
//		获得路径地址,去除参数
		if (url.indexOf("?")!=-1){
			url=url.substring(0,url.indexOf("?"));
		}

		if (url.equals("/login")){
			return null;
		}
		List<String> list=permissionMapper.getPermissionByMenu(url);
		if (list.get(0)==null||list.size()==0){
			return  SecurityConfig.createList("no_role");
//			return SecurityConfig.c
		}
		return SecurityConfig.createList(list.toArray(new String[list.size()]));
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return FilterInvocation.class.isAssignableFrom(aClass);
	}
}
