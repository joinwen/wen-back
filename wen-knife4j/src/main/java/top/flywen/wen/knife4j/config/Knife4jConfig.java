package top.flywen.wen.knife4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhishun.cai
 * @date 2021/3/3 20:30
 */

@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Bean
    public Docket createRestApi() {
        // 添加head参数配置start
        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为swagger2
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("top.flywen.wen.controller")).paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("FlyWen-接口").contact(new Contact("FlyWen", "https://www.flywen.top", "flywen@163.com"))
                .description("FlyWen-接口").version("1.0").termsOfServiceUrl("https://www.flywen.top").build();
    }
}
