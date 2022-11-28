package com.rnd.oauth2authserver.service;

import com.rnd.oauth2authserver.entity.ClientCredential;
import com.rnd.oauth2authserver.repository.ClientCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClientDetailService implements ClientDetailsService {

    @Autowired
    private ClientCredentialRepository clientCredentialRepository;

    @Value("${security.oauth2.client.scope}")
    private String scope;
    @Value("${security.oauth2.client.valid-access-token}")
    private Integer validAccessToken;
    @Value("${security.oauth2.client.authorized-grant-type}")
    private String authorizedGrantType;

    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        ClientCredential clientData = clientCredentialRepository.getClientCredentialById(clientId);

        BaseClientDetails base = new BaseClientDetails();
        base.setClientId(clientData.getClientId());
        base.setClientSecret(getPasswordEncoder().encode(clientData.getClientSecret()));
        base.setScope(Arrays.asList(scope, "write"));
        base.setAccessTokenValiditySeconds(validAccessToken);
        base.setRefreshTokenValiditySeconds(20000);
        base.setAuthorizedGrantTypes(Arrays.asList(authorizedGrantType, "password", "refresh_token"));
        return base;
    }

}
