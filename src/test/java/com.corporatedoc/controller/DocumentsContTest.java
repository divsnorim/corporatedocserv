package com.corporatedoc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import com.corporatedoc.config.MVCConfig;
import com.corporatedoc.model.DocumentComments;
import com.corporatedoc.model.Documents;
import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.DocumentDepartment;
import com.corporatedoc.model.enums.DocumentStatus;
import com.corporatedoc.model.enums.DocumentType;
import com.corporatedoc.repo.BusinessProcessRepo;
import com.corporatedoc.repo.DocumentCommentsRepo;
import com.corporatedoc.repo.DocumentsRepo;
import com.corporatedoc.repo.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@SpringJUnitConfig
@TestPropertySource(locations="classpath:test.properties")
public class DocumentsContTest {

    @Mock
    private DocumentsRepo documentsRepository;

    @Mock
    private UsersRepo usersRepository;

    @Mock
    private BusinessProcessRepo businessProcessRepo;

    @Mock
    private Model model;

    @Mock
    private Users user;

    @Mock
    private MultipartFile multipartFile;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Загрузка файла из ресурсов проекта
        Resource resource = new ClassPathResource("692352a3-c202-432c-bc6a-e76aab0b4e20_Gorovoi_2021.pdf");
        try {
            File file = resource.getFile();
            // Настройка мока для возвращения реального имени файла
            when(multipartFile.getOriginalFilename()).thenReturn(file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @InjectMocks
    private DocumentsCont documentsCont;

//    @Test
//    public void testDocumentAdd() throws IOException {
//        // Mock data
//        String name = "Test Document";
//        String description = "Test Description";
//        Long businessProcessId = 1L;
//        int number = 123;
//        DocumentType documentType = DocumentType.ORDER;
//        DocumentDepartment documentDepartment = DocumentDepartment.LEGAL;
//        String date = "2024-02-26";
//
//        // Stubbing mock methods
//        when(multipartFile.getOriginalFilename()).thenReturn("test_file.jpg");
//        when(usersRepository.save(any())).thenReturn(null);
//
//        // Call method
//        String redirect = documentsCont.DocumentAdd(model, name, description, businessProcessId, number, documentType, documentDepartment, date, multipartFile);
//
//        // Verify interactions
//        verify(multipartFile, times(1)).transferTo(any(File.class));
//        verify(usersRepository, times(1)).save(any());
//
//        // Assert redirect
//        assertEquals("redirect:/document/all", redirect);
//    }

    @Test
    public void testDocumentModification() {
        // Mock data
        Long documentId = 54L;
        Documents document = new Documents(); // Создайте документ для теста
        when(documentsRepository.getReferenceById(documentId)).thenReturn(document);

        // Call method
        String redirect = documentsCont.DocumentModification(documentId);

        // Verify interactions
        verify(documentsRepository, times(1)).getReferenceById(documentId);
        verify(documentsRepository, times(1)).save(document);

        // Assert redirect
        assertEquals("redirect:/document/{id}", redirect);
        // Проверьте, что статус документа изменен на модификацию
        assertEquals(DocumentStatus.MODIFICATION, document.getStatus());
    }

    @Test
    public void testDocumentSigned() {
        // Mock data
        Long documentId = 63L;
        Documents document = new Documents(); // Создайте документ для теста
        when(documentsRepository.getReferenceById(documentId)).thenReturn(document);

        // Call method
        String redirect = documentsCont.DocumentSigned(documentId);

        // Verify interactions
        verify(documentsRepository, times(1)).getReferenceById(documentId);
        verify(documentsRepository, times(1)).save(document);

        // Assert redirect
        assertEquals("redirect:/document/{id}", redirect);
        // Проверьте, что статус документа изменен на подписан
        assertEquals(DocumentStatus.SIGNED, document.getStatus());
    }

}
