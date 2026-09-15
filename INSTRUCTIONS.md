# Smart Clinic — Guide de démarrage (Eclipse)

## 1. Importer le projet dans Eclipse

1. Ouvre Eclipse.
2. `File` → `Import...` → `Maven` → `Existing Maven Projects` → `Next`.
3. Clique `Browse`, sélectionne le dossier `app` (celui qui contient `pom.xml`).
4. Clique `Finish`. Eclipse va télécharger les dépendances (patiente 1-2 min).

## 2. Configurer MySQL

1. Ouvre ton client MySQL (Workbench, terminal, ou phpMyAdmin).
2. Tu n'as **pas besoin** de créer la base à la main — Spring Boot la crée automatiquement
   grâce à `createDatabaseIfNotExist=true` dans `application.properties`.
3. Ouvre le fichier :
   `app/src/main/resources/application.properties`
   et remplace `password` par **ton vrai mot de passe root MySQL** :
   ```
   spring.datasource.password=TON_MOT_DE_PASSE_ICI
   ```

## 3. Lancer l'application

1. Dans Eclipse, clic droit sur `BackEndApplication.java`
   (`app/src/main/java/com/project/back_end/BackEndApplication.java`)
2. `Run As` → `Java Application`.
3. Attends de voir dans la console un message du type :
   `Tomcat started on port 8080` et `Started BackEndApplication`.

Si tu vois une erreur de connexion MySQL, vérifie le mot de passe dans `application.properties`
et que ton service MySQL est bien démarré.

## 4. Ouvrir l'application dans le navigateur

Une fois lancée, ouvre ton navigateur à :

```
http://localhost:8080/
```

Tu arrives sur la page de sélection de rôle. De là tu peux accéder à :
- `http://localhost:8080/adminLogin.html` → **Capture d'écran pour Q13**
- `http://localhost:8080/doctorLogin.html` → **Capture d'écran pour Q14**
- `http://localhost:8080/patientLogin.html` → **Capture d'écran pour Q15**

## 5. Charger les données de test + procédures stockées

1. Une fois l'application lancée AU MOINS UNE FOIS (pour que Hibernate crée les tables),
   arrête-la.
2. Ouvre le fichier `database/stored_procedures.sql` dans ton client MySQL et exécute-le
   en entier. Cela va :
   - Ajouter 1 admin, 3 médecins, 5 patients, 6 rendez-vous de test
   - Créer les 3 procédures stockées demandées

3. Identifiants de test :
   - **Admin** : `admin` / `admin123`
   - **Médecin** : `karim.benali@clinic.com` / `doctor123`
   - **Patient** : `amine.tazi@email.com` / `patient123`

4. Relance l'application (étape 3).

## 6. Captures d'écran à prendre (Q13 à Q18)

- **Q13** : écran de connexion admin (`/adminLogin.html`)
- **Q14** : écran de connexion médecin (`/doctorLogin.html`)
- **Q15** : écran de connexion patient (`/patientLogin.html`)
- **Q16** : connecte-toi en admin (`admin` / `admin123`), tu arrives sur
  `/adminDashboard.html` → remplis le formulaire "Ajouter un médecin" et capture l'écran
  au moment où tu l'ajoutes (ou juste après, avec le message de succès)
- **Q17** : connecte-toi en patient (`amine.tazi@email.com` / `patient123`), tape un nom
  de médecin dans la barre de recherche (ex: "Karim") et capture le résultat
- **Q18** : connecte-toi en médecin (`karim.benali@clinic.com` / `doctor123`), tu verras
  la liste de ses rendez-vous → capture l'écran

## 7. Requêtes SQL (Q19 à Q22)

Dans ton client MySQL, exécute et capture chaque résultat :

```sql
-- Q19
SHOW TABLES;

-- Q20
SELECT * FROM patient LIMIT 5;

-- Q21
CALL GetDailyAppointmentReportByDoctor('2026-09-20');

-- Q22
CALL GetDoctorWithMostPatientsByMonth(9, 2026);

-- Q23 (bonus, mentionné dans le projet original)
CALL GetDoctorWithMostPatientsByYear(2026);
```

## 8. Commandes curl (Q24 à Q26)

Ouvre un terminal (CMD, PowerShell, ou terminal Mac/Linux) pendant que l'application tourne :

```bash
# Q24 - tous les médecins
curl -X GET http://localhost:8080/api/doctor

# Q25 - rendez-vous d'un patient (remplace 1 par l'ID réel du patient, et TOKEN par un vrai token obtenu via /api/patient/login)
curl -X GET http://localhost:8080/api/patient/appointments/1/TOKEN

# Q26 - médecins par spécialité
curl -X GET "http://localhost:8080/api/doctor/search?specialty=Cardiologie"
```

Pour obtenir un token pour Q25, connecte-toi d'abord via curl :
```bash
curl -X POST http://localhost:8080/api/patient/login ^
  -H "Content-Type: application/json" ^
  -d "{\"email\":\"amine.tazi@email.com\",\"password\":\"patient123\"}"
```
(sur Mac/Linux, remplace `^` par `\` en fin de ligne)

Copie le `token` retourné et utilise-le dans la commande Q25.
