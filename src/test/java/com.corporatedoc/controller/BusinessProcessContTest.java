package com.corporatedoc.controller;
import com.corporatedoc.model.BusinessProcess;
import com.corporatedoc.repo.BusinessProcessRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BusinessProcessContTest {

    @InjectMocks
    private BusinessProcessCont businessProcessCont;

    @Mock
    private BusinessProcessRepo businessProcessRepo;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBusinessProcess() {
        // Mock data
        List<BusinessProcess> businessProcesses = new ArrayList<>();
        businessProcesses.add(new BusinessProcess("Process 1"));
        businessProcesses.add(new BusinessProcess("Process 2"));
        when(businessProcessRepo.findAll()).thenReturn(businessProcesses);

        // Call method
        String viewName = businessProcessCont.businessProcess(model);

        // Verify interactions
        verify(businessProcessRepo, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("businessProcess"), anyList());

        // Assert view name
        assertEquals("business_process", viewName);
    }

    @Test
    public void testBusinessProcessAdd() {
        // Mock data
        String processName = "New Process";

        // Call method
        String viewName = businessProcessCont.businessProcessAdd(processName);

        // Verify interactions
        verify(businessProcessRepo, times(1)).save(any(BusinessProcess.class));

        // Assert view name
        assertEquals("redirect:/businessProcess", viewName);
    }

    @Test
    public void testBusinessProcessEdit() {
        // Mock data
        Long id = 1L;
        String newName = "Updated Process";
        BusinessProcess mockedBusinessProcess = new BusinessProcess("Old Process");

        // Mocking behavior
        when(businessProcessRepo.getReferenceById(id)).thenReturn(mockedBusinessProcess);

        // Call method
        String viewName = businessProcessCont.businessProcessEdit(id, newName);

        // Verify interactions
        verify(businessProcessRepo, times(1)).getReferenceById(id);
        verify(businessProcessRepo, times(1)).save(any(BusinessProcess.class));

        // Assert view name
        assertEquals("redirect:/businessProcess", viewName);
        // Assert that the name of the business process was updated
        assertEquals(newName, mockedBusinessProcess.getName());
    }


    @Test
    public void testBusinessProcessDelete() {
        // Mock data
        Long id = 1L;

        // Call method
        String viewName = businessProcessCont.businessProcessDelete(id);

        // Verify interactions
        verify(businessProcessRepo, times(1)).deleteById(id);

        // Assert view name
        assertEquals("redirect:/businessProcess", viewName);
    }
}
