package org.example.springsecurity.controller;

import jakarta.validation.Valid;
import org.example.springsecurity.model.RegistrationForm;
import org.example.springsecurity.service.ServiceDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final ServiceDTO userService;

    public RegistrationController(ServiceDTO userService) {
        this.userService = userService;
    }

    @GetMapping
    public String title(Model model) {
        RegistrationForm userModel = new RegistrationForm();
        model.addAttribute("user", userModel);
        return "registration";
    }

    @PostMapping()
    public String regUser(
            @Valid @ModelAttribute("user") RegistrationForm regForm,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", regForm);
            return "registration";
        } else {
            userService.addUser(regForm.getUsername(), regForm.getEmail(), regForm.getPassword());
            return "redirect:/home";
        }
    }

}