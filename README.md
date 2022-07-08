## Football manager

This football manager service is a simple web application.
- --

### Endpoints:
- GET: /teams - Get all teams
- GET: /teams/id - Get teams by id
- GET: /players/id - Get player by id
- GET: /players - Get all players
- POST: /teams - Create team
- POST: /players - Create player
- PUT: /teams/id - Update team
- PUT: /players/id - Update player
- PUT: /transfers - Crete transfer
- DELETE: /teams/id - Delete team
- DELETE: /players/id - Delete team
- --

### If you want to run this project on your computer, you need:
1. Fork and clone this project:
2. Add Lombok plugin to your IDE
3. Run the application
4. Use Postman for sending your requests during testing this application
- [collection_link](https://www.getpostman.com/collections/3d9ace067f7cb78deb65)
- [capture_link](https://prnt.sc/Wyz2T7Rz6joc)
5. Use H2-database in memory
6. Injection of players and teams are located in config/DataInitializer class.
- --

### Used technologies
- Java 11
- SpringBoot
- SpringBoot Data JPA
- Spring Web
- H2 DB
- Lombok
- Maven checkstyle plugin
