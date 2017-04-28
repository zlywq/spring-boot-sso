package com.test.web1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@SpringBootApplication
@ComponentScan(basePackages = "com.test")
public class Web1Application {
    public static void main(String[] args) {
        SpringApplication.run(Web1Application.class, args);
        
        LoginUrlAuthenticationEntryPoint a;
        OAuth2ClientAuthenticationProcessingFilter b;
        OAuth2RestTemplate c;
        ResourceServerTokenServicesConfiguration d;
        DefaultOAuth2ClientContext e;
    }
}
