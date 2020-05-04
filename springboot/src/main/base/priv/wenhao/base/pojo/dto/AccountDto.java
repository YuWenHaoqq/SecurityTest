package priv.wenhao.base.pojo.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class AccountDto implements UserDetails {
	private Integer accountId;
	private String userName;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String phoneType;
	private Integer enable;
	private Integer delete;
	private List<SimpleGrantedAuthority> permissionName;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissionName;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enable==0?true:false;
	}
}
