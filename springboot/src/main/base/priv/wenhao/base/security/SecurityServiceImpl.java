package priv.wenhao.base.security;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.wenhao.base.exception.BussinessException;
import priv.wenhao.base.pojo.dto.AccountDto;
import priv.wenhao.securitytest.mapper.AccountMapper;
import priv.wenhao.securitytest.pojo.AccountPermissionDto;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SecurityServiceImpl
 * Description: 查询用户信息
 * Author: yuWenHao
 * Date: 2020/4/25
 */

@Service
@Slf4j
@Transactional
public class SecurityServiceImpl implements UserDetailsService {
	@Autowired
	private AccountMapper accountMapper;

	@SneakyThrows
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		List<AccountPermissionDto> list =accountMapper.getAccountByUsername(s);
		if (list.size() == 0) {
			throw new UsernameNotFoundException("用户名不对");
		}
		AccountDto accountDto = new AccountDto();
		accountDto.setUserName(list.get(0).getUserName());
		accountDto.setDelete(list.get(0).getDeleted());
		accountDto.setEmail(list.get(0).getEmail());
		accountDto.setEnable(list.get(0).getEnable());
		accountDto.setPassword(list.get(0).getPassword());
		accountDto.setPhone(list.get(0).getPhone());
		accountDto.setPhoneType(list.get(0).getPhoneType());
		accountDto.setAccountId(list.get(0).getAccountId());
		List<SimpleGrantedAuthority> permissionList = new ArrayList<>();
		for (AccountPermissionDto accountPermissionDto : list) {
			if (accountPermissionDto.getAccountId() != accountDto.getAccountId()) {
				log.info("账号异常:" + accountDto.getAccountId() + "-" + accountPermissionDto.getAccountId());
				throw new BussinessException(-1, "账号异常");
			}
			if (StringUtils.isNotBlank(accountPermissionDto.getPermissionName())) {
				permissionList.add(new SimpleGrantedAuthority(accountPermissionDto.getPermissionName()));
			}
		}
		accountDto.setPermissionName(permissionList);

		return accountDto;
	}
}
