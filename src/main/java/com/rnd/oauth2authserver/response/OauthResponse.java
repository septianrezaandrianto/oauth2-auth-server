package com.rnd.oauth2authserver.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OauthResponse {

    private String access_token;
    private String token_type;
    private Integer expires_in;
    private String scope;
    private String refresh_token;

}
