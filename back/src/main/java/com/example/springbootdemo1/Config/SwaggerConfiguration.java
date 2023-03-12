package com.example.springbootdemo1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 拾光
 * @version 1.0
 * 因为 Spring Boot 暂未提供 Swagger 内置的支持，所以我们需要自己定义配置类
 * 定义配置类 作用：完成SwaggerBean的注入，将SwaggerBean交给springbootIOC容器管理
 */
@Configuration   //（扫包完成注入@Bean）
@EnableSwagger2 // 标记项目启用 Swagger API 接口文档
public class SwaggerConfiguration {

    @Bean
    @Primary    //优先方案，被注解的实现，优先被注入
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springbootdemo1.Controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }

    /**
     * 创建 API 信息   代码的方式创建API信息，也可以通过注解
     * @return  测试示例
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试接口文档示例")
                .description("我是一段描述")
                .version("1.0.0") // 版本号
                .contact(new Contact("芋艿程序员百科", "http://www.iocoder.cn", "1467520438@qq.com")) // 联系人
                .build();
    }

}
