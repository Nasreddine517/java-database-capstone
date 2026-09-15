package com.project.back_end.repo;

import com.project.back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email);

    @Query("SELECT p FROM Patient p WHERE p.email = :login OR p.phone = :login")
    Patient findByEmailOrPhone(@Param("login") String login);
}
