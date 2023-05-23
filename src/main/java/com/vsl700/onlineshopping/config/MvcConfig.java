package com.vsl700.onlineshopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private class UserInterceptor implements HandlerInterceptor{
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
            if(modelAndView != null){
                if(SecurityContextHolder.getContext().getAuthentication() != null && !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
                    modelAndView.addObject("loggedIn", true);
                }else {
                    modelAndView.addObject("loggedIn", false);
                }
            }
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor());
    }

    
}
