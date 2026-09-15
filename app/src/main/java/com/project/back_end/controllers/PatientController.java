package com.project.back_end.controllers;

import com.project.back_end.models.Appointment;
import com.project.back_end.models.Patient;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.services.PatientService;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Patient> register(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.savePatient(patient));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = patientService.validateLogin(
                credentials.get("email"),
                credentials.get("password"));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointments/{patientId}/{token}")
    public ResponseEntity<List<Appointment>> getAppointments(
            @PathVariable Long patientId,
            @PathVariable String token) {

        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }
}
