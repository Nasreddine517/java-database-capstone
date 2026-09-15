async function loadDoctorCards() {
    try {
        const response = await fetch('/api/doctor');
        const doctors = await response.json();
        const tbody = document.getElementById('doctorsBody');
        tbody.innerHTML = '';

        doctors.forEach(doc => {
            const row = document.createElement('tr');
            row.innerHTML = `<td>${doc.name}</td><td>${doc.email}</td><td>${doc.specialty}</td>`;
            tbody.appendChild(row);
        });
    } catch (err) {
        console.error('Error loading doctors', err);
    }
}

document.getElementById('addDoctorForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const doctor = {
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        specialty: document.getElementById('specialty').value,
        phone: document.getElementById('phone').value
    };

    const statusMsg = document.getElementById('statusMsg');

    try {
        const response = await fetch('/api/doctor', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(doctor)
        });

        if (response.ok) {
            statusMsg.style.color = '#27ae60';
            statusMsg.textContent = 'Médecin ajouté avec succès !';
            document.getElementById('addDoctorForm').reset();
            loadDoctorCards();
        } else {
            statusMsg.style.color = '#c0392b';
            statusMsg.textContent = 'Erreur lors de l\'ajout du médecin';
        }
    } catch (err) {
        statusMsg.textContent = 'Erreur de connexion au serveur';
    }
});

document.addEventListener('DOMContentLoaded', loadDoctorCards);
