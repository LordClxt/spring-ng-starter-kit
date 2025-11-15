package com.backend.app.admin;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admins", produces = "application/json")
public class AdminController {
    @GetMapping
    public List<String> getAllAdmins(){
        return List.of("Admin1", "Admin2");
    } 
}
