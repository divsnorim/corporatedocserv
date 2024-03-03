package com.corporatedoc.controller;
import com.corporatedoc.model.enums.Role;
import com.corporatedoc.controller.HumansCont;
import com.corporatedoc.model.Users;
import com.corporatedoc.repo.*;
import com.corporatedoc.service.UsersService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(HumansCont.class)
@ContextConfiguration(classes = TestConfig.class)
public class HumansContTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersService usersService;
    @MockBean
    private UsersRepo usersRepo;
    @MockBean
    private BusinessProcessRepo businessProcessRepo;
    @MockBean
    private DocumentsRepo documentsRepo;
    @MockBean
    private ScoreRepo scoreRepo;
    @MockBean
    private PrimarysRepo primarysRepo;
    @MockBean
    private SecondaryRepo secondaryRepo;
    @MockBean
    private TertiaryRepo tertiaryRepo;
    @MockBean
    private ReviewsRepo reviewsRepo;
    @MockBean
    private ReviewDetailsRepo reviewDetailsRepo;
    @MockBean
    private DocumentCommentsRepo documentCommentsRepo;
    @MockBean
    private AppsRepo appsRepo;

    @Test
    public void testHumanAdd() throws Exception {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        Users newUser = new Users(username, password, Role.CLIENT);

        // Stubbing the usersRepo.findByUsername() method to return null,
        // indicating that the username is not already in use
        when(usersRepo.findByUsername(username)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/humans/add")
                        .param("username", username)
                        .param("password", password))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));

    }
}

