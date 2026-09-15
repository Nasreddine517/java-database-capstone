package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/availability/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        boolean isValid = doctorService.validateToken(token);
        if (!isValid) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired token"));
        }

        List<String> availableSlots = doctorService.getAvailableTimes(doctorId, date);
        return ResponseEntity.ok(Map.of("availableSlots", availableSlots));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> doctorLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = doctorService.validateLogin(
                credentials.get("email"),
                credentials.get("password"));
        return ResponseEntity.ok(response);
    }
}
