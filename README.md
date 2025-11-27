# iban-validator demo project

* Backend: JDK 25, Spring Boot 4, Maven 4
* Database: PostgreSQL 18
* Frontend: React, Vite

To get it up and running:

    mvn clean -DskipTests package
    docker build -t iban-validator-demo .
    docker compose up

Then open `http://localhost:8080` to access the GUI.

