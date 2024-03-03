package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.DocumentComments;
import com.corporatedoc.model.Documents;
import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.DocumentDepartment;
import com.corporatedoc.model.enums.DocumentStatus;
import com.corporatedoc.model.enums.DocumentType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/document")
public class DocumentsCont extends Attributes {
    @GetMapping("/all")
    public String Documents(Model model) {
        AddAttributesDocuments(model);
        return "documents";
    }

    @GetMapping("/my")
    public String DocumentsMy(Model model) {
        AddAttributesDocumentsMy(model);
        return "document_my";
    }

    @GetMapping("/search")
    public String DocumentsSearch(Model model, @RequestParam DocumentStatus documentStatus, @RequestParam DocumentType documentType, @RequestParam DocumentDepartment documentDepartment, @RequestParam String date) {
        AddAttributesDocumentsSearch(model, documentStatus, documentType, documentDepartment, date);
        return "documents";
    }

    @GetMapping("/{id}")
    public String Document(Model model, @PathVariable Long id) {
        AddAttributesDocument(model, id);
        return "document";
    }

    @PostMapping("/comment/add/{id}")
    public String DocumentCommentAdd(@PathVariable Long id, @RequestParam String text) {
        documentCommentsRepo.save(new DocumentComments(text, documentsRepo.getReferenceById(id), getUser()));
        return "redirect:/document/{id}";
    }

    @GetMapping("/{id}/modification")
    public String DocumentModification(@PathVariable Long id) {
        Documents document = documentsRepo.getReferenceById(id);
        document.setStatus(DocumentStatus.MODIFICATION);
        documentsRepo.save(document);
        return "redirect:/document/{id}";
    }

    @GetMapping("/{id}/signed")
    public String DocumentSigned(@PathVariable Long id) {
        Documents document = documentsRepo.getReferenceById(id);
        document.setStatus(DocumentStatus.SIGNED);
        documentsRepo.save(document);
        return "redirect:/document/{id}";
    }

    @GetMapping("/delete/{id}")
    public String DocumentDelete(@PathVariable Long id) {
        documentsRepo.deleteById(id);
        return "redirect:/document/all";
    }

    @GetMapping("/edit/{id}")
    public String DocumentEdit(Model model, @PathVariable Long id) {
        AddAttributesDocumentEdit(model, id);
        return "document_edit";
    }

    @GetMapping("/add")
    public String DocumentAdd(Model model) {
        AddAttributesDocumentAdd(model);
        return "document_add";
    }

    @PostMapping("/transfer/{id}")
    public String DocumentTransfer(@RequestParam DocumentDepartment documentDepartment, @PathVariable Long id) {
        Documents document = documentsRepo.getReferenceById(id);
        document.setDepartment(documentDepartment);
        documentsRepo.save(document);
        return "redirect:/document/{id}";
    }

    @PostMapping("/add")
    public String DocumentAdd(Model model, @RequestParam String name, @RequestParam String description, @RequestParam Long businessProcessId, @RequestParam int number, @RequestParam DocumentType documentType, @RequestParam DocumentDepartment documentDepartment, @RequestParam String date, @RequestParam MultipartFile document) {
        String result = "";
        try {
            uploadImg = "D:/bsuir/7 semester/corporate/src/main/resources/img";
            if (document != null && !Objects.requireNonNull(document.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                result = "documents/" + uuidFile + "_" + document.getOriginalFilename();
                document.transferTo(new File(uploadImg + "/" + result));
            }
        } catch (IOException e) {
            AddAttributesDocumentAdd(model);
            model.addAttribute("message", "Некорректные данные!");
            return "document_add";
        }

        Documents newDocument = new Documents(name, number, documentType, documentDepartment, date, result, businessProcessRepo.getReferenceById(businessProcessId), description);

        Users user = getUser();

        user.addDocument(newDocument);

        usersRepo.save(user);
        return "redirect:/document/all";
    }

    @PostMapping("/edit/{id}")
    public String DocumentEdit(Model model, @RequestParam String name, @RequestParam String description, @RequestParam Long businessProcessId, @RequestParam int number, @RequestParam DocumentType documentType, @RequestParam DocumentDepartment documentDepartment, @RequestParam String date, @RequestParam MultipartFile document, @PathVariable Long id) {
        Documents oldDocument = documentsRepo.getReferenceById(id);
        try {
            if (document != null && !Objects.requireNonNull(document.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String result = "documents/" + uuidFile + "_" + document.getOriginalFilename();
                document.transferTo(new File(uploadImg + "/" + result));
                oldDocument.setDocument(result);
            }
        } catch (IOException e) {
            AddAttributesDocumentEdit(model, id);
            model.addAttribute("message", "Некорректные данные!");
            return "document_edit";
        }

        oldDocument.set(name, number, documentType, documentDepartment, date, businessProcessRepo.getReferenceById(businessProcessId), description);

        documentsRepo.save(oldDocument);

        return "redirect:/document/{id}";
    }
}
