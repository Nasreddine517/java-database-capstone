package com.project.back_end.services;

import com.project.back_end.models.Admin;
import com.project.back_end.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TokenService tokenService;

    public Map<String, Object> validateLogin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);

        if (admin == null || !admin.getPassword().equals(password)) {
            return Map.of("success", false, "message", "Invalid credentials");
        }

        String token = tokenService.generateToken(admin.getUsername());
        return Map.of("success", true, "token", token);
    }
}
