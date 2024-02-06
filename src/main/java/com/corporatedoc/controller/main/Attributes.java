package com.corporatedoc.controller.main;

import com.corporatedoc.model.enums.*;
import org.springframework.ui.Model;

public class Attributes extends Main {

    protected void AddAttributes(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected void AddAttributesEnums(Model model) {
        model.addAttribute("yesnos", YesNo.values());
        model.addAttribute("citizenships", Citizenship.values());
        model.addAttribute("maritals", Marital.values());
        model.addAttribute("origins", Origin.values());
        model.addAttribute("disabilities", Disability.values());
        model.addAttribute("documentTypes", DocumentType.values());
        model.addAttribute("documentDepartments", DocumentDepartment.values());
        model.addAttribute("documentStatuses", DocumentStatus.values());
        model.addAttribute("businessProcess", businessProcessRepo.findAll());
    }

    protected void AddAttributesDocuments(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("documents", documentsRepo.findAll());
    }

    protected void AddAttributesDocumentsMy(Model model) {
        AddAttributes(model);
        model.addAttribute("documents", getUser().getDocuments());
    }

    protected void AddAttributesDocumentsSearch(Model model, DocumentStatus documentStatus, DocumentType documentType, DocumentDepartment documentDepartment, String date) {
        AddAttributes(model);
        AddAttributesEnums(model);
        if (date.isEmpty()) {
            model.addAttribute("documents", documentsRepo.findAllByStatusAndDepartmentAndType(documentStatus, documentDepartment, documentType));
        } else {
            model.addAttribute("documents", documentsRepo.findAllByDateAndStatusAndDepartmentAndType(date, documentStatus, documentDepartment, documentType));
        }
        model.addAttribute("documentStatus", documentStatus);
        model.addAttribute("documentType", documentType);
        model.addAttribute("documentDepartment", documentDepartment);
        model.addAttribute("date", date);
    }

    protected void AddAttributesDocument(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("document", documentsRepo.getReferenceById(id));
    }

    protected void AddAttributesDocumentEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("document", documentsRepo.getReferenceById(id));
    }

    protected void AddAttributesDocumentAdd(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
    }

    protected void AddAttributesUsers(Model model) {
        AddAttributes(model);
        model.addAttribute("users", usersRepo.findAllByOrderByRole());
        model.addAttribute("roles", Role.values());
    }

    protected void AddAttributesHuman(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesProfile(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", getUser());
    }

    protected void AddAttributesScore(Model model) {
        AddAttributes(model);
        model.addAttribute("human", getUser());
    }


    protected void AddAttributesHumanEdit(Model model, Long id) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("human", usersRepo.getReferenceById(id));
    }

    protected void AddAttributesCatalog(Model model) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("humans", usersRepo.findAllByRole(Role.CLIENT));
    }

    protected void AddAttributesReviews(Model model) {
        AddAttributes(model);
        model.addAttribute("reviews", reviewsRepo.findAll());
    }

    protected void AddAttributesReviewDetails(Model model, Long id) {
        AddAttributes(model);
        model.addAttribute("review", reviewsRepo.getReferenceById(id));
    }

    protected void AddAttributesApps(Model model) {
        AddAttributes(model);
        if (getRole().equals(Role.MANAGER.toString())) {
            model.addAttribute("apps", appsRepo.findAll());
        } else if (getRole().equals(Role.CLIENT.toString())) {
            model.addAttribute("apps", appsRepo.findAllByOwner(getUser()));
        }
    }

    protected void AddAttributesCatalogSearchStaff(Model model, Marital marital, Origin origin, Citizenship citizenship, YesNo retiree, YesNo conscripted, Disability disability) {
        AddAttributes(model);
        AddAttributesEnums(model);
        model.addAttribute("humans", usersRepo.findAllByTertiary_MaritalAndTertiary_OriginAndTertiary_CitizenshipAndTertiary_RetireeAndTertiary_ConscriptedAndTertiary_Disability(marital, origin, citizenship, retiree, conscripted, disability));
        model.addAttribute("marSelect", marital);
        model.addAttribute("oriSelect", origin);
        model.addAttribute("citSelect", citizenship);
        model.addAttribute("retSelect", retiree);
        model.addAttribute("conSelect", conscripted);
        model.addAttribute("disSelect", disability);
    }
}
