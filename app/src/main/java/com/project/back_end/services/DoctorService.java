package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TokenService tokenService;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<String> getAvailableTimes(Long doctorId, String date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // In a real implementation, this would filter out already booked slots
        // for the given date against the doctor's availableTimes list.
        return doctor.getAvailableTimes();
    }

    public boolean validateToken(String token) {
        return tokenService.validateToken(token);
    }

    public Map<String, Object> validateLogin(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);

        if (doctor == null || !doctor.getPassword().equals(password)) {
            return Map.of("success", false, "message", "Invalid credentials");
        }

        String token = tokenService.generateToken(doctor.getEmail());
        return Map.of("success", true, "token", token);
    }
}
