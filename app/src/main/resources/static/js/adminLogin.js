document.getElementById('adminLoginForm').addEventListener('submit', async function (e) {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const errorMsg = document.getElementById('errorMsg');

    try {
        const response = await fetch('/api/admin/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (data.success) {
            localStorage.setItem('token', data.token);
            localStorage.setItem('role', 'admin');
            window.location.href = '/adminDashboard.html';
        } else {
            errorMsg.textContent = data.message || 'Identifiants invalides';
        }
    } catch (err) {
        errorMsg.textContent = 'Erreur de connexion au serveur';
    }
});
