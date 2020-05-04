package priv.wenhao.base.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class UrlAccessDecisionManager implements AccessDecisionManager {
	@Override
	public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
		Iterator<ConfigAttribute> iterator=collection.iterator();
		while (iterator.hasNext()){
			ConfigAttribute ca=iterator.next();
//			当前请求需要权限
			String needRole=ca.getAttribute();
			if ("login_role".equals(needRole)) {
				if (authentication instanceof AnonymousAuthenticationToken) {
					throw new BadCredentialsException("未登录");
				} else
					return;
			}
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			for (GrantedAuthority authority:authorities){
				if (authority.getAuthority().equals(needRole)) {
					return;
				}
			}
		}
		throw new AccessDeniedException("权限不足");

	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return true;
	}
}
