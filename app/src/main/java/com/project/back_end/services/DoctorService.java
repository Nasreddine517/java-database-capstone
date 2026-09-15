package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
=======
import java.time.LocalDate;
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
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

<<<<<<< HEAD
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public List<Doctor> findByName(String name) {
        return doctorRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Doctor> findBySpecialty(String specialty) {
        return doctorRepository.findBySpecialtyIgnoreCase(specialty);
    }

    public List<String> getAvailableTimes(Long doctorId, String date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
=======
    public List<String> getAvailableTimes(Long doctorId, String date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // In a real implementation, this would filter out already booked slots
        // for the given date against the doctor's availableTimes list.
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
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
<<<<<<< HEAD
        return Map.of("success", true, "token", token, "doctorId", doctor.getId());
=======
        return Map.of("success", true, "token", token);
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
    }
}
