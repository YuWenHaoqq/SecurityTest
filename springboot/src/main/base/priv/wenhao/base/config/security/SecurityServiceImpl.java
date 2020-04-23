package priv.wenhao.base.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.wenhao.base.pojo.UserPojo;
import priv.wenhao.base.pojo.dto.SchoolTeacherDto;
import priv.wenhao.base.pojo.dto.SysRoleDto;
import priv.wenhao.securitytest.mapper.SchoolTeacherMapper;
import priv.wenhao.securitytest.mapper.SysRoleMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SecurityServiceImpl implements UserDetailsService {
	@Autowired
	private SchoolTeacherMapper schoolTeacherMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		QueryWrapper<SchoolTeacherDto> queryWrapper=new QueryWrapper<SchoolTeacherDto>()
				.eq("teacher_id",s)
				.eq("is_deleted",0);
		List<SchoolTeacherDto> list=schoolTeacherMapper.selectList(queryWrapper);
		if (list==null||list.size()==0){
			throw new UsernameNotFoundException("用户名不对");
		}
		List<String> roleName=sysRoleMapper.getRoleNameById(s);
//		if (roleName==null){
//			roleName=new ArrayList<>();
//		}
		UserPojo userPojo=new UserPojo();
		userPojo.setTeacherId(list.get(0).getTeacherId());
		userPojo.setEnable(list.get(0).getEnable());
		userPojo.setPassword(list.get(0).getPassword());
		userPojo.setRoles(roleName);
		return userPojo;
	}
}
