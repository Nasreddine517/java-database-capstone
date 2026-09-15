document.getElementById('patientLoginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const errorMsg = document.getElementById('errorMsg');

    try {
        const response = await fetch('/api/patient/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();

        if (data.success) {
            localStorage.setItem('token', data.token);
            localStorage.setItem('role', 'patient');
            localStorage.setItem('patientId', data.patientId);
            window.location.href = '/patientDashboard.html';
        } else {
            errorMsg.textContent = data.message || 'Identifiants invalides';
        }
    } catch (err) {
        errorMsg.textContent = 'Erreur de connexion au serveur';
    }
});
