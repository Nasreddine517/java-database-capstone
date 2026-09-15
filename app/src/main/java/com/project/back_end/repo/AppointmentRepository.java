package com.project.back_end.repo;

import com.project.back_end.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.doctor.id = :doctorId AND FUNCTION('DATE', a.appointmentTime) = :date")
    List<Appointment> findByDoctorIdAndAppointmentDate(@Param("doctorId") Long doctorId, @Param("date") LocalDate date);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDoctorId(Long doctorId);
}
