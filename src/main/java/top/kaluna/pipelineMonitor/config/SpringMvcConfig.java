package top.kaluna.pipelineMonitor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.kaluna.pipelineMonitor.interceptor.LoginInterceptor;

import javax.annotation.Resource;

/**
 * @author Yuery
 * @date 2022/2/24/0024 - 17:50
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Resource
    LoginInterceptor loginInterceptor;

    public void addInterceptor(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/test/**",
                        "/redis/**",
                        "/user/login",
                        "/pv/list",
                        "/bpr/list",
                        "/bpr/update",
                        "/fbg/list",
                        "/nr/total",
                        "/fbg/abnormalList",
                        "/fbg/temperatureNow"
                        )
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**","/doc.html", "/swagger-ui.html/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

}
