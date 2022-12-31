package com.rnd.oauth2authserver.controller;

import com.google.gson.Gson;
import com.rnd.oauth2authserver.constant.OauthConstant;
import com.rnd.oauth2authserver.entity.ClientCredential;
import com.rnd.oauth2authserver.repository.ClientCredentialRepository;
import com.rnd.oauth2authserver.request.LoginRequest;
import com.rnd.oauth2authserver.response.ClientCredentialResponse;
import com.rnd.oauth2authserver.response.OauthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/login")
@RestController
public class OauthController {

    Logger logger = LoggerFactory.getLogger(OauthController.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ClientCredentialRepository clientCredentialRepository;

    @Value("${security.oauth2.resource.oauth-url}")
    private String oauthUrl;

    @PostMapping("/clientCredential")
    public ResponseEntity<ClientCredentialResponse> loginClientCredential(@RequestHeader(value = OauthConstant.AUTHORIZATION_HEADER,
            required = false) String authorization, @RequestHeader(value = OauthConstant.X_CLIENT_KEY_HEADER,
            required = false) String clientKey, @RequestBody LoginRequest loginRequest) {

        String responseCode = null;
        String responseMessage = null;
        Gson gson = new Gson();

        ClientCredentialResponse result = new ClientCredentialResponse();
        ClientCredential clientCredential = clientCredentialRepository.getClientCredentialById(clientKey);

        String credential = null;
        if (clientCredential != null) {
            String idSecret = clientCredential.getClientId() + ":" + clientCredential.getClientSecret();
            credential = new String(org.apache.commons.codec.binary.Base64.encodeBase64(idSecret.getBytes()));
            logger.error("credential : " + credential);
        }

        if (clientCredential == null) {
            if (clientKey == null || clientKey.equalsIgnoreCase(OauthConstant.EMPTY_STRING)) {
                responseCode = OauthConstant.INVALID_CLIENT_KEY_STATUS;
                responseMessage = OauthConstant.EMPTY_MESSAGE
                        .replace("{variable}", OauthConstant.X_CLIENT_KEY_HEADER);
                return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                        responseMessage), HttpStatus.BAD_REQUEST);
            } else {
                responseCode = OauthConstant.UNAUTHORIZED_STATUS;
                responseMessage = OauthConstant.UNAUTHORIZED_MESSAGE
                        .replace("{variable}", "Unknown Client");
                return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                        responseMessage), HttpStatus.UNAUTHORIZED);
            }
        } else if (!clientKey.equalsIgnoreCase(clientCredential.getClientId())) {
            responseCode = OauthConstant.UNAUTHORIZED_STATUS;
            responseMessage = OauthConstant.UNAUTHORIZED_MESSAGE
                    .replace("{variable}", "Unknown Client");
            return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                    responseMessage), HttpStatus.UNAUTHORIZED);
        } else if (authorization == null || authorization.equalsIgnoreCase(OauthConstant.EMPTY_STRING)) {
            responseCode = OauthConstant.INVALID_AUTH_STATUS;
            responseMessage = OauthConstant.EMPTY_MESSAGE
                    .replace("{variable}", OauthConstant.AUTHORIZATION_HEADER);
            return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                    responseMessage), HttpStatus.BAD_REQUEST);
        } else if (!authorization.equalsIgnoreCase("Basic " + credential)) {
            responseCode = OauthConstant.UNAUTHORIZED_UNKNOWN_STATUS;
            responseMessage = OauthConstant.UNAUTHORIZED_UNKNOWN_MESSAGE
                    .replace("{variable}", OauthConstant.AUTHORIZATION_HEADER);
            return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                    responseMessage), HttpStatus.UNAUTHORIZED);
        } else if (!loginRequest.getGrantType().equalsIgnoreCase("client_credentials")) {
            responseCode = OauthConstant.INVALID_STATUS;
            responseMessage = OauthConstant.INVALID_MESSAGE
                    .replace("{variable}", "grantType");
            return new ResponseEntity<ClientCredentialResponse>(new ClientCredentialResponse(responseCode,
                    responseMessage), HttpStatus.BAD_REQUEST);
        }


        HttpHeaders headers = new HttpHeaders();
        headers.add(OauthConstant.CONTENT_TYPE_HEADER, OauthConstant.CONTENT_TYPE_VALUE);
        headers.add(OauthConstant.AUTHORIZATION_HEADER, authorization);
        headers.add(OauthConstant.X_CLIENT_KEY_HEADER, clientKey);

        HttpEntity<Object> entity = new HttpEntity<>(loginRequest, headers);
        logger.error("Entity : " + gson.toJson(entity).replaceAll("\\\\u003d", "="));

        String accessTokenUrl = oauthUrl + "?grant_type=" + loginRequest.getGrantType();
        logger.error("URL : " + accessTokenUrl);

        ResponseEntity<OauthResponse> data = restTemplate.exchange(accessTokenUrl,
                HttpMethod.POST,
                entity, new ParameterizedTypeReference<OauthResponse>() {
                }
        );

        responseCode = OauthConstant.SUCCESS_STATUS;
        responseMessage = OauthConstant.SUCCESS_MESSAGE;
        result.setResponseCode(responseCode);
        result.setResponseMessage(responseMessage);
        result.setAccessToken(data.getBody().getAccess_token());
        result.setTokenType(data.getBody().getToken_type());
        result.setExpiresIn(data.getBody().getExpires_in());
        result.setScope(data.getBody().getScope());
        return ResponseEntity.ok(result);
    }
}