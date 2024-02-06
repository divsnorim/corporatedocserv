package com.corporatedoc.controller;

import com.corporatedoc.controller.main.Attributes;
import com.corporatedoc.model.Users;
import com.corporatedoc.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Attributes {
    @GetMapping
    public String subs(Model model) {
        AddAttributesUsers(model);
        return "users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @RequestParam Role role) {
        Users user = usersRepo.getReferenceById(id);
        user.setRole(role);
        usersRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        usersRepo.deleteById(id);
        return "redirect:/users";
    }
}
