package com.musicreview.api.security;

import com.musicreview.api.config.SecretsConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class SecurityConstants {
    public static final long JWT_EXPIRATION = 3600000;
    public static String JWT_SECRET;
    @Autowired
    public SecurityConstants(SecretsConfigProperties secretsConfigProperties) {
        SecurityConstants.JWT_SECRET = secretsConfigProperties.jwtSecret();
    }
}
