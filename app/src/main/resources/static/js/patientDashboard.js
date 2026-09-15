async function filterDoctorsOnChange() {
    const query = document.getElementById('searchInput').value.trim();
    const container = document.getElementById('resultsContainer');
    container.innerHTML = '';

    if (query.length === 0) {
        return;
    }

    try {
        const response = await fetch(`/api/doctor/search?name=${encodeURIComponent(query)}`);
        const doctors = await response.json();

        if (doctors.length === 0) {
            container.innerHTML = '<p style="margin-top:15px;color:#7f8c8d;">Aucun médecin trouvé</p>';
            return;
        }

        doctors.forEach(doc => {
            const card = document.createElement('div');
            card.className = 'doctor-card';
            card.innerHTML = `<h4>${doc.name}</h4><p>${doc.specialty}</p><p>${doc.email}</p>`;
            container.appendChild(card);
        });
    } catch (err) {
        console.error('Error searching doctors', err);
    }
}
