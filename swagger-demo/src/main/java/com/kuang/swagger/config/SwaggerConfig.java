package com.kuang.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * @author shaoshao
 * @version 1.0
 * @date 2020/10/9 20:12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置了swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment) {

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev", "test");


        //获取项目环境
        boolean flag = environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("少司命")
                .enable(flag) //是否启用swagger
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kuang.swagger.controller"))
                //.paths(PathSelectors.ant("/kuang/**")) //过滤什么路径
                .build();
    }

    @Bean
    public Docket docket1() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("A");
    }

    @Bean
    public Docket docket2() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("B");
    }

    @Bean
    public Docket docket3() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("C");
    }

    private ApiInfo apiInfo() {
        //作者信息
        Contact contact = new Contact("少司命", "http://www.4399.com", "1600767556@qq.com");

        return new ApiInfo(
                "少司命的SwaggerApi文档"
                , "待厉兵秣马后,就是要逆天而行"
                , "v1.0"
                , "https://www.4399.com"
                , contact
                , "Apach2.0"
                , "http://www.apache.org/licences/LICENSE-2.0"
                , new ArrayList()
        );
    }


}
