package com.example.springboot.configs;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
        @Bean
        public OpenAPI myOpenAPI() {
                Server devServer = new Server();
                devServer.setUrl("http://localhost:8081");
                devServer.setDescription("Server URL in Development environment");

                Contact contact = new Contact();
                contact.setEmail("julia.carolinedasilva2003@gmail.com");
                contact.setName("Júlia");

                Info info = new Info()
                        .title("Projeto Crud de Produtos")
                        .version("1.0")
                        .contact(contact);

                return new OpenAPI().info(info).servers(List.of(devServer));
        }
}