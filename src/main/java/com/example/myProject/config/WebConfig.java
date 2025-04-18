package com.example.myProject.config;

import com.example.myProject.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)                       // 인터셉터 실행 순서
                .addPathPatterns("/**")         // 모든 요청 경로에 적용
                .excludePathPatterns(           // 로그인 없이 접근해야 하는 경로는 제외
                        "/",
                        "/login",
                        "/signup",
                        "/css/**",
                        "/js/**"
                );
    }
}
