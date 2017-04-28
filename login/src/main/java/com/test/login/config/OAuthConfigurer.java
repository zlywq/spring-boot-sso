package com.test.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
public class OAuthConfigurer extends AuthorizationServerConfigurerAdapter {

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource(
//                "keystore.jks"), "tc123456".toCharArray()).getKeyPair("tycoonclient");
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("tomcat1.keystore"), "123456".toCharArray())
//        		.getKeyPair("tomcat1");
//        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("localhost.jks"), "123456".toCharArray())
//        		.getKeyPair("alias1", "123456".toCharArray());
        
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("serverKeystore.jks"), "123456".toCharArray())
        		.getKeyPair("alias1", "123456".toCharArray());
        
        converter.setKeyPair(keyPair);
        
        //converter.setSigningKey("123");
        
        
        return converter;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {

        clients.inMemory().withClient("ssoclient").secret("ssosecret")
                .autoApprove(true)
                .authorizedGrantTypes("authorization_code", "refresh_token").scopes("openid");
                //.scopes("openid","read","write");
                //.authorizedGrantTypes("password","authorization_code", "refresh_token","implicit","client_credentials");//.scopes("openid");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
    
        security.tokenKeyAccess("permitAll()").checkTokenAccess(
                "isAuthenticated()").allowFormAuthenticationForClients()
        	//.sslOnly();//...
        ;
        
        //TokenEndpoint te;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
    	
        endpoints.accessTokenConverter(jwtAccessTokenConverter())
        //.authenticationManager(getAuthenticationManager())
        ;
    }
    
    
    
//    @Bean
//    public AuthenticationManager getAuthenticationManager(){
//    	OAuth2AuthenticationManager manager = new OAuth2AuthenticationManager();
//    	
//
//    	//CustomJdbcClientDetailsService
//    	/*
//create table sql and insert table sql ......
//
//new ClientDetailsService(){
//			@Override
//			public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//    	}
//    	 */
//    	manager.setClientDetailsService( new CustomJdbcClientDetailsService...);
//    	
//    	return manager;
//    }
//    

}
