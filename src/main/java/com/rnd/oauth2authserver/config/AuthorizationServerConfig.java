package com.rnd.oauth2authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.scope}")
    private String scope;
    @Value("${security.oauth2.client.valid-access-token}")
    private Integer validAccessToken;
    @Value("${security.oauth2.client.authorized-grant-type}")
    private String authorizedGrantType;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .authorizedGrantTypes(authorizedGrantType)
                .secret(encoder().encode(clientSecret))
                .scopes(scope)
                .redirectUris("localhost:8081/authentication/api/hello")
                .accessTokenValiditySeconds(validAccessToken)
                .autoApprove(false);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
