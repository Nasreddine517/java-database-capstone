function createPatientRow(appointment) {
    const row = document.createElement('tr');
    const patientName = appointment.patient ? appointment.patient.name : 'N/A';
    const time = appointment.appointmentTime ? appointment.appointmentTime.replace('T', ' ') : '';
    row.innerHTML = `<td>${patientName}</td><td>${time}</td><td>${appointment.status || ''}</td>`;
    return row;
}

async function loadAppointments() {
    const doctorId = localStorage.getItem('doctorId') || 1;
    const tbody = document.getElementById('patientTableBody');
    tbody.innerHTML = '';

    try {
        const response = await fetch(`/api/appointment/doctor/${doctorId}`);
        const appointments = await response.json();

        appointments.forEach(appt => {
            tbody.appendChild(createPatientRow(appt));
        });
    } catch (err) {
        console.error('Error loading appointments', err);
    }
}

document.addEventListener('DOMContentLoaded', loadAppointments);
