package com.htisec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "User API", version = "1.0", description = "User Information"))
@SecurityScheme(name = "htisec", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication
public class SampleApp {


    public static void main(String[] quartet) {
        //InitDB initDB = new InitDB();
        //initDB.initialize();
		SpringApplication.run(SampleApp.class, quartet);
    }
}

