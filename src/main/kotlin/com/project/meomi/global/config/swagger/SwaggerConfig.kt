package com.project.meomi.global.config.swagger

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiKey
import springfox.documentation.service.AuthorizationScope
import springfox.documentation.service.Contact
import springfox.documentation.service.SecurityReference
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun docket(): Docket = Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.project.meomi"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .securityContexts(listOf(securityContext()))
        .securitySchemes(listOf(apiKey()))

    @Bean
    fun securityContext(): SecurityContext =
        SecurityContext.builder()
            .securityReferences(defaultAuth())
            .operationSelector { true }
            .build()

    private fun defaultAuth(): List<SecurityReference> =
        arrayOf(AuthorizationScope("global", "accessEverything"))
            .let { listOf(SecurityReference("Authorization", it)) }

    private fun apiKey() = ApiKey("Authorization", "Authorization", "header")

    private fun apiInfo() = ApiInfoBuilder()
        .title("S&C API")
        .description("광소마 5기들의 팀 묘미분식 서비스 API 입니다.")
        .contact(Contact("성길", "https://github.com/SungGil-5125", "sunggil@0125@naver.com"))
        .version("1.0").build()
}