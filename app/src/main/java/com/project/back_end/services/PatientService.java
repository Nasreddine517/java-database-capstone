package com.project.back_end.services;

import com.project.back_end.models.Patient;
import com.project.back_end.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TokenService tokenService;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Map<String, Object> validateLogin(String email, String password) {
        Patient patient = patientRepository.findByEmail(email);

        if (patient == null || !patient.getPassword().equals(password)) {
            return Map.of("success", false, "message", "Invalid credentials");
        }

        String token = tokenService.generateToken(patient.getEmail());
        return Map.of("success", true, "token", token, "patientId", patient.getId());
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email);
    }
}
