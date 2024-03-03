package com.corporatedoc.controller;

import com.corporatedoc.service.UsersService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@TestConfiguration
@ComponentScan(basePackages = "com.corporatedoc") // Подставьте ваш пакет с бинами
public class TestConfig {

}
