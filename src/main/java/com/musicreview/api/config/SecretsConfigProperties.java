package com.musicreview.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("config")
public record SecretsConfigProperties(String clientId, String clientSecret, String jwtSecret) {
}
