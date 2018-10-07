package com.example.demo.config;

import com.example.demo.helpers.ServerContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Bean
    public TomcatEmbeddedServletContainerFactory tomcatFactory() {
        return new ServerContainerFactory();
    }
}
