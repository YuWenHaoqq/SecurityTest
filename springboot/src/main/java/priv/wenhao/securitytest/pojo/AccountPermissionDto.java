package priv.wenhao.securitytest.pojo;

import lombok.Data;

@Data
public class AccountPermissionDto {
	private Integer accountId;
	private String userName;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String phoneType;
	private Integer enable;
	private Integer deleted;
	private String permissionName;
	private String permissionDes;
}
