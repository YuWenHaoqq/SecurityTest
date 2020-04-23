package priv.wenhao.securitytest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"priv.wenhao.securitytest", "priv.wenhao.base"})
@ServletComponentScan(basePackages = {"priv.wenhao.securitytest", "priv.wenhao.base"})
@MapperScan(basePackages = {"priv.wenhao.securitytest.mapper"})
//启动定时器
@EnableScheduling
public class SecuritytestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuritytestApplication.class, args);
		System.out.println("Ready Perfectly!");
	}

}
