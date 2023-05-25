package com.apress.quickpoll;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class QuickPollApplication {


	public static void main(String[] args) {
		SpringApplication.run(QuickPollApplication.class, args);
	}

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI().components(new Components()).info(new Info().
				version("2.0").
				title("QuickPoll REST API").
				description("QuickPoll Api for creating and managing polls").
				contact(new Contact().email("info@example.com")).
				license(new License().url("https://opensource.org/license/mit/").name("MIT License")).
				termsOfService("http://example.com/terms-of-service"));
	}
}
