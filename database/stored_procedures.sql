-- ============================================
-- Smart Clinic Management System
-- Stored Procedures + Sample Data
-- Run this AFTER the Spring Boot app has started once
-- (so that Hibernate has already created the tables)
-- ============================================

USE smart_clinic;

-- ============================================
-- SAMPLE DATA
-- ============================================

INSERT INTO admin (username, password) VALUES ('admin', 'admin123');

INSERT INTO doctor (name, email, password, specialty, phone) VALUES
('Dr. Karim Benali', 'karim.benali@clinic.com', 'doctor123', 'Cardiologie', '0600000001'),
('Dr. Sara Amrani', 'sara.amrani@clinic.com', 'doctor123', 'Dermatologie', '0600000002'),
('Dr. Youssef Idrissi', 'youssef.idrissi@clinic.com', 'doctor123', 'Pédiatrie', '0600000003');

INSERT INTO patient (name, email, password, phone, address) VALUES
('Amine Tazi', 'amine.tazi@email.com', 'patient123', '0611111111', 'Rabat'),
('Fatima Zahra', 'fatima.zahra@email.com', 'patient123', '0622222222', 'Salé'),
('Omar Lahlou', 'omar.lahlou@email.com', 'patient123', '0633333333', 'Casablanca'),
('Nadia Chraibi', 'nadia.chraibi@email.com', 'patient123', '0644444444', 'Rabat'),
('Hicham Bennani', 'hicham.bennani@email.com', 'patient123', '0655555555', 'Fes');

INSERT INTO appointment (doctor_id, patient_id, appointment_time, status) VALUES
(1, 1, '2026-09-20 09:00:00', 'SCHEDULED'),
(1, 2, '2026-09-20 10:00:00', 'SCHEDULED'),
(1, 3, '2026-09-21 09:00:00', 'SCHEDULED'),
(2, 4, '2026-09-20 11:00:00', 'SCHEDULED'),
(2, 5, '2026-09-22 14:00:00', 'SCHEDULED'),
(3, 1, '2026-09-25 15:00:00', 'SCHEDULED');

-- ============================================
-- PROCEDURE 1: Daily appointment report by doctor
-- ============================================
DELIMITER $$

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN report_date DATE)
BEGIN
    SELECT
        d.id AS doctor_id,
        d.name AS doctor_name,
        COUNT(a.id) AS total_appointments
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    WHERE DATE(a.appointment_time) = report_date
    GROUP BY d.id, d.name;
END$$

DELIMITER ;

-- ============================================
-- PROCEDURE 2: Doctor with most patients in a given month
-- ============================================
DELIMITER $$

CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN input_month INT, IN input_year INT)
BEGIN
    SELECT
        d.id AS doctor_id,
        d.name AS doctor_name,
        COUNT(DISTINCT a.patient_id) AS total_patients
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    WHERE MONTH(a.appointment_time) = input_month
      AND YEAR(a.appointment_time) = input_year
    GROUP BY d.id, d.name
    ORDER BY total_patients DESC
    LIMIT 1;
END$$

DELIMITER ;

-- ============================================
-- PROCEDURE 3: Doctor with most patients in a given year
-- ============================================
DELIMITER $$

CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN input_year INT)
BEGIN
    SELECT
        d.id AS doctor_id,
        d.name AS doctor_name,
        COUNT(DISTINCT a.patient_id) AS total_patients
    FROM appointment a
    JOIN doctor d ON a.doctor_id = d.id
    WHERE YEAR(a.appointment_time) = input_year
    GROUP BY d.id, d.name
    ORDER BY total_patients DESC
    LIMIT 1;
END$$

DELIMITER ;

-- ============================================
-- USAGE EXAMPLES (for Q19-Q22)
-- ============================================
-- SHOW TABLES;
-- SELECT * FROM patient LIMIT 5;
-- CALL GetDailyAppointmentReportByDoctor('2026-09-20');
-- CALL GetDoctorWithMostPatientsByMonth(9, 2026);
-- CALL GetDoctorWithMostPatientsByYear(2026);
