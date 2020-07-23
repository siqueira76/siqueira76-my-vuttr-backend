package ccom.siqueira76.vuttr.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors
				.basePackage("ccom.siqueira76.vuttr.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"VUTTR | BackEnd Restfull | API", 
				"Very Useful Tools to Remember", 
				"Versão 0.0.1",
				"https://github.com/siqueira76/siqueira76-my-vuttr-backend.git", 
				new Contact("José Carlos Siqueira", "https://www.linkedin.com/in/siqueira1/", "josecarlos.siqueira76@gmail.com"), 
				"Open source", 
				"https://github.com/siqueira76/siqueira76-my-vuttr-backend.git", 
				Collections.emptyList());
	}


}
