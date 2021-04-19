package cn.edu.fzu.daoyun;

import cn.edu.fzu.interceptor.AuthenticationInterceptor;
import cn.edu.fzu.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration  //既保留了所有的自动配置，也能用我们扩展的配置；
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AuthenticationInterceptor());
    }

    @Override
    /*请求跨域*/
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
//                // 再次加入前端Origin
                .allowedOrigins("http://127.0.0.1:6677")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                //允许请求头
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

}
