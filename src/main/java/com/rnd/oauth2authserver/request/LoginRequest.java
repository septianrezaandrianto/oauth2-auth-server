package com.rnd.oauth2authserver.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;
    private String grantType;

}
