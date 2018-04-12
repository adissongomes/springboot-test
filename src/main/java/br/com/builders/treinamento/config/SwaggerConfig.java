/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SwaggerConfig.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.config;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.service.Tags;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.*;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.builders.treinamento"))
				.paths(PathSelectors.any()).build().directModelSubstitute(DateTime.class, String.class);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
				"Customer Relationship Management API",
				"Customer API",
				"1.0.0",
				"",
				new Contact("Adisson Gomes", "", "adisson.gomes@gmail.com"),
				"Apache 2.0",
				"http://www.apache.org/licenses/LICENSE-2.0.html");
	}
}
