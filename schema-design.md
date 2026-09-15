# Database Schema Design — Smart Clinic Management System

## MySQL Database Design

### Table: doctor
| Field | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| specialty | VARCHAR(100) | NOT NULL |
| phone | VARCHAR(20) | |

### Table: patient
| Field | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| name | VARCHAR(100) | NOT NULL |
| email | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |
| phone | VARCHAR(20) | |

### Table: appointment
| Field | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| doctor_id | BIGINT | FOREIGN KEY REFERENCES doctor(id) |
| patient_id | BIGINT | FOREIGN KEY REFERENCES patient(id) |
| appointment_time | DATETIME | NOT NULL |
| status | VARCHAR(20) | e.g. SCHEDULED, CANCELLED, COMPLETED |

### Table: admin
| Field | Type | Constraints |
|---|---|---|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| username | VARCHAR(100) | UNIQUE, NOT NULL |
| password | VARCHAR(255) | NOT NULL |

## Relationships

- One **doctor** can have many **appointments** (1 to many)
- One **patient** can have many **appointments** (1 to many)
- Each **appointment** links exactly one doctor and one patient (many-to-one on both sides)

## MongoDB Design (Prescription collection)

```json
{
  "_id": "ObjectId",
  "appointmentId": "Long",
  "patientName": "String",
  "medication": ["String"],
  "dosage": "String",
  "doctorNotes": "String"
}
```
