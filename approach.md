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
### Backend und Frontend verbinden
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
* Spring-Security
* Frontend-Tests
* Mockito-Warnings bzgl. Javaagent und Java 25