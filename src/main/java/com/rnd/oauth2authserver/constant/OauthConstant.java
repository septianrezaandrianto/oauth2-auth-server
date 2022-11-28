package com.rnd.oauth2authserver.constant;

public interface OauthConstant {
    static String CONTENT_TYPE_HEADER ="Content-Type";
    static String CONTENT_TYPE_VALUE ="application/json";
    static String AUTHORIZATION_HEADER = "Authorization";
    static String X_CLIENT_KEY_HEADER = "X-CLIENT-KEY";
    static String EMPTY_STRING ="";
    static String INVALID_CLIENT_KEY_STATUS = "4001201";
    static String EMPTY_MESSAGE = "Invalid mandatory field [{variable}]";
    static String UNAUTHORIZED_STATUS = "4011201";
    static String UNAUTHORIZED_MESSAGE = "Unauthorized. [{variable}]";
    static String INVALID_AUTH_STATUS = "4011202";
    static String UNAUTHORIZED_UNKNOWN_STATUS = "4011203";
    static String UNAUTHORIZED_UNKNOWN_MESSAGE = "Unauthorized. [Unknown {variable}]";
    static String INVALID_STATUS = "4001202";
    static String INVALID_MESSAGE = "Invalid field format [{variable}]";
    static String SUCCESS_STATUS = "2001201";
    static String SUCCESS_MESSAGE = "SUCCESS";

}
