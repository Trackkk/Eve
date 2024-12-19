# Event Organizer
This is an Event Organizer web application built as part of a university project. It provides a simple platform for managing events, including basic CRUD operations. Users can create, read, update, and delete events. Additionally, users can buy tickets for events, view their tickets, and search for events with various filters.

## Events page example:
![](Events.png)

## MyTickets page example:
![](MyTickets.png)

# Installation Instructions:
### 1.  Clone the repository to your local machine.
### 2. Install dependencies: ``` npm install```
### 3.  Run the backend server (Spring Boot):
  - ### Use ```mvn spring-boot:run``` to start the backend server.
### 4. Run the frontend:
  - ### Use ```ng serve``` to start the frontend development server.
### 5. Open your browser and navigate to ```http://localhost:4200``` to access the application.

# Technologies Used:
- ### Frontend: Angular
- ### Backend: Java (Spring Boot)
- ###  Database: MySQL
- ### Authentication: JWT (JSON Web Tokens)
- ### UI: Bootstrap, Material Design Components

# Usage:
- ### User Registration: New users can register by providing their name, email, and password.
- ### Login: After registration, users can log in using their credentials.
- ### Event Management: Event creators can add new events, edit existing ones, or delete events.
- ### Ticket Management: Users can buy tickets for events and view their purchased tickets under the "My Tickets" section.
- ### Filters: The event list can be filtered by event name, location, and date range
