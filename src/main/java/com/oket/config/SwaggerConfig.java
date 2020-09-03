package com.oket.config;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通过@Configuration注解，让Spring来加载该类配置。再通过@EnableSwagger2注解来启用Swagger2
 * 通过createRestApi函数创建Docket的Bean之后，apiInfo()用来创建该Api的基本信息（这些基本信息会展现在文档页面中）。
 * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
 * 〈swagger配置〉
 *
 * @author ysh
 * @create 2019/2/26
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	// 定义分隔符
	private static final String splitor = ";";

	/**
	 * @Description: swagger 配置扫描多个路径（在原来的基础上扩展）
	 */
	public static Predicate<RequestHandler> basePackage(final String basePackage) {
		return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
	}

	private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
		return input -> {
			// 循环判断匹配
			for (String strPackage : basePackage.split(splitor)) {
				boolean isMatch = input.getPackage().getName().startsWith(strPackage);
				if (isMatch) {
					return true;
				}
			}
			return false;
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		return Optional.fromNullable(input.declaringClass());
	}

	/**
	 * url中的特殊字符
	 */
	@Bean
	public ServletWebServerFactory webServerFactory() {
		TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
		fa.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
		return fa;
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(basePackage("com.oket"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("容积表诊断与校正管理平台使用Swagger2构建RESTful APIs")
				.description("容积表诊断与校正管理平台")
//                .termsOfServiceUrl("http://127.0.0.1:8080")
				.contact(new Contact("青岛澳科软件", "", ""))
				.version("1.0")
				.build();
	}
}
