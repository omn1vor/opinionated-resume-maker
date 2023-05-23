package org.omnivor.opinionatedresume.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    LocaleChangeInterceptor lci;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lci);
    }
}
