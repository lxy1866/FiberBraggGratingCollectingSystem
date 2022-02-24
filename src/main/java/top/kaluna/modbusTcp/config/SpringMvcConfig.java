//package top.kaluna.modbusTcp.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import top.kaluna.modbusTcp.interceptor.LogInterceptor;
//
//import javax.annotation.Resource;
//
///**
// * @author Yuery
// * @date 2022/2/24/0024 - 17:50
// */
//@Configuration
//public class SpringMvcConfig implements WebMvcConfigurer {
//    @Resource
//    LogInterceptor logInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(logInterceptor)
//                .addPathPatterns("/**").excludePathPatterns("/login");
//    }
//
//}
