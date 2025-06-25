# BudgetingApp
# BudgetingApp

A simple CLI-based personal finance application built in Java. Designed to help users track their income and expenses with support for user profiles, password security, and basic financial insights. The app demonstrates clean architecture principles, testability, and solid object-oriented design.

## Features

- Secure user login with password hashing
- Record income and expenses via categorized transactions
- Calculate current balance
- User profile management (name, DOB, location)
- Persistence using PostgreSQL and JDBC
- Unit testing with JUnit
- Dependency Injection & Interface-based architecture
- Follows SOLID principles & clean code practices

##  Project Structure

```
src/
 └── main/java/com/danstoneley/bankingapp/
      ├── dao/                # Interfaces and DAO classes
      ├── service/            # Business logic
      ├── models/             # Data models (User, Transaction)
      ├── session/            # Session management layers
      ├── config/             # Database configuration
      ├── exceptions/         # Custom exceptions
      ├── utils/              # Utility functions (e.g., password hashing)
      ├── ui/                 # UI display logic
      └── Main.java           # Application entry point
```

## Running Tests

The application includes a suite of JUnit tests to ensure core DAO functionality. Test users are dynamically created and cleaned up using utility methods.

```bash
mvn test
```

> Note: Tests are integration-style, validating actual DB interactions.

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/BudgetingApp.git
   cd BudgetingApp
   ```

2. **Configure the database**  
   Update `Database.java` with your PostgreSQL URL, username, and password.

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.danstoneley.bankingapp.Main"
   ```

## Technologies Used

- Java 17+
- Maven
- PostgreSQL
- JUnit 5
- JDBC
- BCrypt for password hashing

##  Design Principles

- Follows **SOLID** and **OOP** principles
- Uses **Dependency Inversion** for testability
- Separation of concerns between UI, Service, DAO, and Model layers
- Graceful error handling via custom exceptions (`DataAccessException`)
