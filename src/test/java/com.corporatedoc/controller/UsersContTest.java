package com.corporatedoc.controller;
import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.Role;
import com.corporatedoc.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UsersContTest {

    @Mock
    private UsersRepo usersRepository;

    @InjectMocks
    private UsersCont usersCont;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEditUser() {
        // Подготовка данных
        Long userId = 1L;
        Role newRole = Role.ADMIN;

        // Создание мок-объекта Users, реализующего UserDetails
        Users user = mock(Users.class);
        when(user.getUsername()).thenReturn("username"); // Пример метода из UserDetails
        when(usersRepository.getReferenceById(userId)).thenReturn(user);

        // Вызов метода контроллера
        String result = usersCont.editUser(userId, newRole);

        // Проверка вызова методов и возвращаемого результата
        verify(usersRepository, times(1)).getReferenceById(userId);
        verify(usersRepository, times(1)).save(user); // Можно также использовать eq(user)
        assert result.equals("redirect:/users");
    }
}


