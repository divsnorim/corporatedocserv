package com.corporatedoc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.corporatedoc.controller.LoginCont;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

class LoginContTest {

    @InjectMocks
    private LoginCont loginController;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogin() {
        String viewName = loginController.login(model);
        assertEquals("login", viewName); // Проверяем, что возвращается правильное представление

        // Проверяем, что метод AddAttributes() вызывается
        verify(model).addAttribute("role", "NOT");
        verify(model).addAttribute("user", null);
    }
}
