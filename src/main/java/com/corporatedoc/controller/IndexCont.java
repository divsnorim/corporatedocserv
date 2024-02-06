package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.enums.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Attributes {

    @GetMapping
    public String index1() {
        if (getUser() == null) {
            return "redirect:/login";
        } else {
            return "redirect:/profile";
        }
    }

    @GetMapping("/index")
    public String index2() {
        if (getUser() == null) {
            return "redirect:/login";
        } else {
            return "redirect:/profile";
        }
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        AddAttributesCatalog(model);
        return "humans";
    }

    @GetMapping("/catalog/search")
    public String catalogSearch(Model model, @RequestParam Marital marital, @RequestParam Origin origin, @RequestParam Citizenship citizenship,
                                @RequestParam YesNo retiree, @RequestParam YesNo conscripted, @RequestParam Disability disability) {
        AddAttributesCatalogSearchStaff(model, marital, origin, citizenship, retiree, conscripted, disability);
        return "humans";
    }
}