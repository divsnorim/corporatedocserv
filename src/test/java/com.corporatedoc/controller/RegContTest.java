package com.corporatedoc.controller;

import com.corporatedoc.controller.RegCont;
import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.Role;
import com.corporatedoc.repo.UsersRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegContTest {

    @Mock
    private UsersRepo usersRepo;

    @Mock
    private Model model;

    @InjectMocks
    private RegCont regCont;

    @Test
    public void testRegExistingUser() {
        when(usersRepo.findAll()).thenReturn(Collections.singletonList(new Users("accountant", "password", Role.CLIENT)));

        String result = regCont.regUser(model, "accountant", "password");

        assertEquals("redirect:/login", result);
        verify(usersRepo, times(1)).save(any());
    }

    @Test
    public void testRegNewUser() {
        // в базе данных нет пользователя с именем "testuser1"

        String result = regCont.regUser(model, "testuser1", "password");

        assertEquals("redirect:/login", result);
        verify(usersRepo, times(1)).save(any());
    }

}
