package com.test.resource;

import java.security.KeyPair;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

//@Configuration
//@EnableResourceServer
public class MyOAuth2ResourceServerConfig
//extends ResourceServerConfigurerAdapter
{  
//    @Override  
//    public void configure(ResourceServerSecurityConfigurer config) {  
//        config.tokenServices(tokenServices());  
//    }  
//   
//    @Bean  
//    public TokenStore tokenStore() {  
//        return new JwtTokenStore(accessTokenConverter());  
//    }
//   
//    @Bean  
//    public JwtAccessTokenConverter accessTokenConverter() {  
////        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();  
////        converter.setSigningKey("123");  
////        return converter;  
//        
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("localhost.jks"), "123456".toCharArray())
//      		.getKeyPair("alias1", "123456".toCharArray());
//      
//        converter.setKeyPair(keyPair);
//        //converter.setSigningKey("123");
//
//        return converter;
//    }  
//   
//    @Bean  
//    @Primary  
//    public DefaultTokenServices tokenServices() {  
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();  
//        defaultTokenServices.setTokenStore(tokenStore());  
//        return defaultTokenServices;  
//    }  
}  
