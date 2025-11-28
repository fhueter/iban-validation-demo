# Vorgehensweise IBAN-Validator-Demo

## Maven-Multi-Modul-Projektstruktur erstellen
### Docker-Compose-File erstellen (zunächst nur PostgreSQL-Image)
### Spring Backend Hülle erstellen
... unter Zuhilfenahme von Spring Initializr (Maven, Spring 4, JDK 25):
* Spring Web
* Spring Data JPA
* PostgreSQL Driver
* Liquibase
* Lombok
### React Frontend Hülle erstellen
`npx create-react-router@latest --template remix-run/react-router-templates/javascript`
### Backend und Frontend "verbinden"
### Docker/Docker-Compose vervollständigen 

## Backend-Logik implementieren
* Bankverzeichnis mit Identifizierung
  * Model
  * Repository
  * Service
* IBAN-Validierung
  * Controller
* Bank hinzufügen
## Frontend für benötigte Funktionalität erstellen
* IBAN-Validierung inkl. Rückgabe des Banknamens
* Bank hinzufügen

## ToDo
Backend:
* Spring-Security
* Logging
* "Sprechende" Backend-Validierungsfehlermeldungen

Backend (technische Schulden)
* Passwörter auslagern!
* Diverse Warnings beim Maven-Build, z.B. "A restricted method in java.lang.System has been called"
* Mockito-Warnings bzgl. Javaagent und Java 25

Frontend:
* Frontend- sowie End-To-End-Tests
* Frontend-Eingabevalidierungen
* vieles mehr ...

Fachlich:
* Internationale Banken unterstützen

