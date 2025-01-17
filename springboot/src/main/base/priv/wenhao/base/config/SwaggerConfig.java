package priv.wenhao.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SwaggerConfig
 * Description: 关于swagger的配置类
 * Author: yuWenHao
 * Date: 2020/4/23
 */


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket createRestApi() {
		//在配置好的配置类中增加此段代码即可
		ParameterBuilder ticketPar = new ParameterBuilder();
		ParameterBuilder ticketPar2=new ParameterBuilder();
		ParameterBuilder ticketPar3=new ParameterBuilder();
		List<Parameter> pars = new ArrayList<Parameter>();
		ticketPar.name("token").description("登录校验")//name表示名称，description表示描述
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();//required表示是否必填，defaultvalue表示默认值

		ticketPar2.name("aes").description("随机生成的res")
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();
//		pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效
//
//		pars.add(ticketPar2.build());
		ticketPar3.name("id").description("用于提供的stuid或teacherid")
				.modelRef(new ModelRef("string")).parameterType("header")
				.required(false).build();

		pars.add(ticketPar.build());//添加完此处一定要把下边的带***的也加上否则不生效
		pars.add(ticketPar2.build());
		pars.add(ticketPar3.build());


		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(pars);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("springsecurity")
				.description("大道之心永不止步-余文豪")
				.version("1.0.0")
				.contact(new Contact("余文豪", "", "1471689575@qq.com"))
				.termsOfServiceUrl("")
				.build();
	}
}
