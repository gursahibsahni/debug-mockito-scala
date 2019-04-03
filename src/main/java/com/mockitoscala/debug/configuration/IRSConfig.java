package com.mockitoscala.debug.configuration;

import com.mockitoscala.debug.argumentresolver.IRSArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class IRSConfig implements WebMvcConfigurer {

    IRSArgumentResolver irsArgumentResolver;

    public IRSConfig(IRSArgumentResolver irsArgumentResolver) {
        this.irsArgumentResolver = irsArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(irsArgumentResolver);
    }
}
