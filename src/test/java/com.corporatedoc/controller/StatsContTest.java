package com.corporatedoc.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.corporatedoc.repo.*;
import com.corporatedoc.service.UsersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StatsCont.class)
public class StatsContTest {

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
    @WithMockUser(username="manager", roles={"MANAGER"})
    public void testStats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
