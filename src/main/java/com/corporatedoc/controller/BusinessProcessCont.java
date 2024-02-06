package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.BusinessProcess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/businessProcess")
public class BusinessProcessCont extends Attributes {

    @GetMapping
    public String businessProcess(Model model) {
        AddAttributes(model);
        model.addAttribute("businessProcess", businessProcessRepo.findAll());
        return "business_process";
    }

    @PostMapping("/add")
    public String businessProcessAdd(@RequestParam String name) {
        businessProcessRepo.save(new BusinessProcess(name));
        return "redirect:/businessProcess";
    }

    @PostMapping("/edit/{id}")
    public String businessProcessEdit(@PathVariable Long id, @RequestParam String name) {
        BusinessProcess businessProcess = businessProcessRepo.getReferenceById(id);
        businessProcess.setName(name);
        businessProcessRepo.save(businessProcess);
        return "redirect:/businessProcess";
    }

    @GetMapping("/delete/{id}")
    public String businessProcessDelete(@PathVariable Long id) {
        businessProcessRepo.deleteById(id);
        return "redirect:/businessProcess";
    }
}
