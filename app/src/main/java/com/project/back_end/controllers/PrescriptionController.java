package com.project.back_end.controllers;

import com.project.back_end.models.Prescription;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/{token}")
    public ResponseEntity<Map<String, Object>> savePrescription(
            @PathVariable String token,
            @RequestBody Prescription prescription) {

        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired token"));
        }

        try {
            prescriptionService.savePrescription(prescription);
            return ResponseEntity.ok(Map.of("message", "Prescription saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("message", "Error saving prescription: " + e.getMessage()));
        }
    }

    @GetMapping("/{appointmentId}/{token}")
    public ResponseEntity<Map<String, Object>> getPrescription(
            @PathVariable Long appointmentId,
            @PathVariable String token) {

        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired token"));
        }

        Prescription prescription = prescriptionService.getPrescriptionByAppointmentId(appointmentId);
        return ResponseEntity.ok(Map.of("prescription", prescription));
    }
}
