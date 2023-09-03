package com.example.taskList.service.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix="security.jwt")//read data from .yaml file
public class JwtProperties {
    private String secret;
    private Long access;
    private Long refresh;
}
