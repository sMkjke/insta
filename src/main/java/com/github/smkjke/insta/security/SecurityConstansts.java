package com.github.smkjke.insta.security;

public class SecurityConstansts {

    /**
     * two ** means every url may be here
     */
    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String SECRET = "SecretKeyGenJJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 600_000; //10min 1
    public static final String CONTENT_TYPE = "applicationJson";
}
