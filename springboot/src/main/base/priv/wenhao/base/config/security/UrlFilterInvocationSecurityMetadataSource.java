package priv.wenhao.base.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import priv.wenhao.base.pojo.dto.SysPermissionDto;
import priv.wenhao.securitytest.mapper.SysPermissionMapper;

import java.util.Collection;
import java.util.List;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private SysPermissionMapper sysPermissionMapper;
	@Override
	public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) o).getRequestUrl();
		if ("/login".equals(requestUrl)) {
			return null;
		}
		List<SysPermissionDto> permissionDtoList=sysPermissionMapper.getPermissionByUrl(requestUrl);
		for (SysPermissionDto sysPermissionDto:permissionDtoList){

		}
		return SecurityConfig.createList("ROLE_LOGIN");
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
