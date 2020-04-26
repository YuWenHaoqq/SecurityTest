import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class Stest {
	@Test
	public void test(){
//		对密码进行加密
		String haspas= BCrypt.hashpw("123",BCrypt.gensalt());
		System.out.println(haspas);
//		校验密码和BCrypt密码是否一致
//		通过不同的盐生成的密码都可以被校验
		boolean checkpw=BCrypt.checkpw("123","$2a$10$e1uqxZLQ/tisdEUcgU5LVewVmQbfqFN2N1xiz5MrHptuceXz/qSEa");
		System.out.println(checkpw);
	}
}
