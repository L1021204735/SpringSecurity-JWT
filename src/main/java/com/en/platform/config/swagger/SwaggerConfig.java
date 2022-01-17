package com.en.platform.config.swagger;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * @author liuxiaobai
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.en.platform")
                .title("练手项目")
                .description("练手项目相关接口文档")
                .contactName("liuxiabai")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
