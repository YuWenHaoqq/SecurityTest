package priv.wenhao.base.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: SecurityServiceImpl
 * Description: 查询用户信息
 * Author: yuWenHao
 * Date: 2020/4/25
 */

@Service
@Transactional
public class SecurityServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//		QueryWrapper<SchoolTeacherDto> queryWrapper=new QueryWrapper<SchoolTeacherDto>()
//				.eq("teacher_id",s)
//				.eq("is_deleted",0);
//		List<SchoolTeacherDto> list=schoolTeacherMapper.selectList(queryWrapper);
//		if (list==null||list.size()==0){
//			throw new UsernameNotFoundException("用户名不对");
//		}
//		List<String> roleName=sysRoleMapper.getRoleNameById(s);
////		if (roleName==null){
////			roleName=new ArrayList<>();
////		}
//		UserPojo userPojo=new UserPojo();
//		userPojo.setTeacherId(list.get(0).getTeacherId());
//		userPojo.setEnable(list.get(0).getEnable());
//		userPojo.setPassword(list.get(0).getPassword());
//		userPojo.setRoles(roleName);
		UserDetails userPojo = User.withUsername("admin").password("$2a$10$mBlHrBIWRbZn6u3tzmsuV.fqYrFAmqobTBYO0X5tmUPjO1CbdYd5.")
				.authorities("p1").build();
		return userPojo;
	}
}
