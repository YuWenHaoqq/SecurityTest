package priv.wenhao.securitytest.mapper;

import org.apache.ibatis.annotations.Mapper;
import priv.wenhao.base.pojo.dto.AccountDto;
import priv.wenhao.securitytest.pojo.AccountPermissionDto;

import java.util.List;

@Mapper
public interface AccountMapper {
	public List<AccountPermissionDto> getAccountByUsername(String username);

}
