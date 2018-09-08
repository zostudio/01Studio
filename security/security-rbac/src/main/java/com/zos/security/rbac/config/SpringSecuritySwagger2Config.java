package com.zos.security.rbac.config;

import com.zos.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SpringSecuritySwagger2Config {

    // 安全配置
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public Docket api() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization").defaultValue(securityProperties.getSwagger().getToken()).description("令牌(bearer)").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        tokenPar =  new ParameterBuilder();
        tokenPar.name("device_id").defaultValue(securityProperties.getSwagger().getDeviceId()).description("机器码").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(pars).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口").description("接口文档与测试").termsOfServiceUrl("https://github.com").license("Apache License 2.0")
                .licenseUrl("http://www.apache.org/licenses/").build();
    }
}
