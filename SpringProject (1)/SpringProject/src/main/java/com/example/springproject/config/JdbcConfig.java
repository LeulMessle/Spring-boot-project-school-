package com.example.springproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJdbcRepositories(basePackages = "com.example.springproject.repository")
@EnableTransactionManagement
public class JdbcConfig {
}