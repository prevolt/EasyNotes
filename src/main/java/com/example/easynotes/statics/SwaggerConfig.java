package com.example.easynotes.statics;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.service.Contact;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;

import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.example.easynotes.controllers"))
                        .paths(PathSelectors.any())
                        .build()
                        .apiInfo(apiInfo())
                        .securitySchemes(Arrays.asList(apiKey()));
        }

        @Override
        protected void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

                registry.addResourceHandler("/webjars/**")
                                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

        private Predicate<String> postPaths() {
                return or(regex("/api/*.*"));
        }

        private ApiInfo apiInfo() {
                return new ApiInfoBuilder().title("Delvrt APIs").description("API Specification - v1.0")
                                .termsOfServiceUrl("https://delvrt.com")
                                .contact(new Contact("Harsh Chauhan", "https://delvrt.com/#about/",
                                                "delvrt33@gmail.com"))
                                // .license("Delvrt License")
                                // .licenseUrl("delvrt33@gmail.com")
                                .license("Apache License Version 2.0")
                                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"").version("1.0").build();
        }

        private ApiKey apiKey() {
                return new ApiKey("authkey", "Authorization", "header");
        }

}