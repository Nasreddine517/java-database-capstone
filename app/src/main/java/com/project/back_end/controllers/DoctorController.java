package com.project.back_end.controllers;

import com.project.back_end.models.Doctor;
import com.project.back_end.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
<<<<<<< HEAD
@RequestMapping("/api/doctor")
=======
@RequestMapping("/doctor")
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/availability/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

<<<<<<< HEAD
        if (!doctorService.validateToken(token)) {
=======
        boolean isValid = doctorService.validateToken(token);
        if (!isValid) {
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
            return ResponseEntity.status(401).body(Map.of("message", "Invalid or expired token"));
        }

        List<String> availableSlots = doctorService.getAvailableTimes(doctorId, date);
        return ResponseEntity.ok(Map.of("availableSlots", availableSlots));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
<<<<<<< HEAD
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty) {

        if (name != null) {
            return ResponseEntity.ok(doctorService.findByName(name));
        }
        if (specialty != null) {
            return ResponseEntity.ok(doctorService.findBySpecialty(specialty));
        }
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
=======
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> doctorLogin(@RequestBody Map<String, String> credentials) {
        Map<String, Object> response = doctorService.validateLogin(
                credentials.get("email"),
                credentials.get("password"));
        return ResponseEntity.ok(response);
    }
}
