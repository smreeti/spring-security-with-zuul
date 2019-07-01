package com.f1soft.adminservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author smriti on 6/30/19
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "admin")
public class StartupProperties {

    private String username;

    private String password;

    private Character status;

    private String emailAddress;

    private Integer loginAttempt;
}

