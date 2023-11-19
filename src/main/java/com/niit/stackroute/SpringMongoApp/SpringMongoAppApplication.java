package com.niit.stackroute.SpringMongoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title ="mongo API", version = "1.0",description = "Customer App"))
public class SpringMongoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoAppApplication.class, args);
	}

}
