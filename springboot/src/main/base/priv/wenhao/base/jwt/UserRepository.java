package priv.wenhao.base.jwt;

import org.springframework.data.repository.CrudRepository;
import priv.wenhao.base.pojo.dto.AccountDto;

public interface UserRepository extends CrudRepository<AccountDto,Integer> {
	AccountDto findByusername(String username);
}
