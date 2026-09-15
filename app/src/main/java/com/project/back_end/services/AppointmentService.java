package com.project.back_end.services;

import com.project.back_end.models.Appointment;
<<<<<<< HEAD
import com.project.back_end.repo.AppointmentRepository;
=======
import com.project.back_end.repository.AppointmentRepository;
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }

    public boolean cancelAppointment(Long appointmentId) {
        if (appointmentRepository.existsById(appointmentId)) {
            appointmentRepository.deleteById(appointmentId);
            return true;
        }
        return false;
    }

    public List<Appointment> getAppointmentsByPatient(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
<<<<<<< HEAD

    public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
=======
>>>>>>> 888c0cef715d6e797292871e44c03b9ef87c6a0d
}
