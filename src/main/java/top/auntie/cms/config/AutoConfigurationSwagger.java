package top.auntie.cms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import top.auntie.cms.properties.AutoConfigurationSwaggerProperties;

/**
 * @Description
 * @Author Mr Cui
 * @Date 2020/3/27 16:22
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(AutoConfigurationSwaggerProperties.class)
@ConditionalOnProperty(name = "swagger.show", havingValue = "true")
@ComponentScan({"top.auntie.cms.config", "top.auntie.cms.properties"})
@EnableSwagger2
public class AutoConfigurationSwagger {

    @Autowired
    private AutoConfigurationSwaggerProperties autoConfigurationSwaggerProperties;

    //参考注解
    //https://blog.csdn.net/xupeng874395012/article/details/68946676
    //访问地址
    //http://ip:port/swagger-ui.html

    @Bean
    public Docket createRestApi() {

        /*ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("Cookie").description("Cookie").
                modelRef(new ModelRef("string")).parameterType("cookie").required(true).build();
        parameters.add(parameterBuilder.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                //正式服务器设置为false
                .enable(autoConfigurationSwaggerProperties.getShow())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        autoConfigurationSwaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build()/*.globalOperationParameters(parameters)*/;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(autoConfigurationSwaggerProperties.getTitle())
                .description(autoConfigurationSwaggerProperties.getDescription())
                .contact(new Contact("Regan", "http://www.bif-tech.com/", autoConfigurationSwaggerProperties.getContactEmail()))
                .version(autoConfigurationSwaggerProperties.getVersion())
                .build();
    }

}
