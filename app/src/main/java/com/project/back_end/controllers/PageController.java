package com.project.back_end.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "redirect:/roleSelection.html";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "forward:/adminLogin.html";
    }

    @GetMapping("/doctor/login")
    public String doctorLogin() {
        return "forward:/doctorLogin.html";
    }

    @GetMapping("/patient/login")
    public String patientLogin() {
        return "forward:/patientLogin.html";
    }
}
