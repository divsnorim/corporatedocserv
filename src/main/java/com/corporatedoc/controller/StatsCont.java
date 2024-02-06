package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.BusinessProcess;
import com.corporatedoc.model.Score;
import com.corporatedoc.model.enums.YesNo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Attributes {
    @GetMapping
    public String Stats(Model model) {
        AddAttributes(model);

        List<BusinessProcess> businessProcesses = businessProcessRepo.findAll();

        String[] signedString = new String[businessProcesses.size()];
        int[] signedInt = new int[businessProcesses.size()];

        for (int i = 0; i < businessProcesses.size(); i++) {
            signedString[i] = businessProcesses.get(i).getName();
            signedInt[i] = businessProcesses.get(i).getSignedCount();
        }

        model.addAttribute("signedString", signedString);
        model.addAttribute("signedInt", signedInt);

        String[] modString = new String[businessProcesses.size()];
        int[] modInt = new int[businessProcesses.size()];

        for (int i = 0; i < businessProcesses.size(); i++) {
            modString[i] = businessProcesses.get(i).getName();
            modInt[i] = businessProcesses.get(i).getModCount();
        }

        model.addAttribute("modString", modString);
        model.addAttribute("modInt", modInt);

        String[] notSignedString = new String[businessProcesses.size()];
        int[] notSignedInt = new int[businessProcesses.size()];

        for (int i = 0; i < businessProcesses.size(); i++) {
            notSignedString[i] = businessProcesses.get(i).getName();
            notSignedInt[i] = businessProcesses.get(i).getNotSignedCount();
        }

        model.addAttribute("notSignedString", notSignedString);
        model.addAttribute("notSignedInt", notSignedInt);

        businessProcesses.sort(Comparator.comparing(BusinessProcess::getDocumentsSize));
        Collections.reverse(businessProcesses);

        String[] topQuantityName = new String[5];
        int[] topQuantityNumber = new int[5];
        for (int i = 0; i < businessProcesses.size(); i++) {
            if (i == 5) break;
            topQuantityName[i] = businessProcesses.get(i).getName();
            topQuantityNumber[i] = businessProcesses.get(i).getDocumentsSize();
        }
        model.addAttribute("topQuantityName", topQuantityName);
        model.addAttribute("topQuantityNumber", topQuantityNumber);

        return "stats";
    }
}