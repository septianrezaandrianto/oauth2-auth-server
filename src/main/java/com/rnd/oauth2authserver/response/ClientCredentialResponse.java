package com.rnd.oauth2authserver.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientCredentialResponse {

    private String accessToken;
    private String tokenType;
    private Integer expiresIn;
    private String responseCode;
    private String responseMessage;
    private String scope;

    public ClientCredentialResponse(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
