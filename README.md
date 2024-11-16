# 🚗G0🏍️

### A Comprehensive Object-Oriented Car and Motorcycle Rental Application

---

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [OOP Principles](#oop-principles)
- [Class Structure](#class-structure)
- [Setup and Execution](#setup-and-execution)
- [Future Enhancements](#future-enhancements)
- [Screenshots](#screenshots)
- [Contact](#contact)

---

## Overview

The **G0** is a Java-based application that demonstrates advanced object-oriented programming (OOP) concepts such as **abstraction, inheritance, encapsulation,** and **polymorphism**. The project was developed to streamline vehicle rentals, including both cars and motorcycles, with features for managing rental transactions and vehicle availability.

---

## Features

- **Vehicle Management**: Add and manage various vehicles, including cars and motorcycles.
- **Rental Functionality**: Users can rent and return vehicles while the system updates availability.
- **User Interface**: A simple, interactive text-based menu for easy navigation.
- **Customizable Pricing**: Calculate rental costs based on the type of vehicle and rental duration.
- **Vehicle Details**: Display additional information like fuel type and number of seats for cars.

---

## Technologies Used

- **Language**: Java
- **Concepts**: Object-Oriented Programming (OOP)
- **Tools**: IDE (e.g.IntelliJ), JDK 11 or above

---

## OOP Principles

This project demonstrates the four pillars of OOP:

1. **Abstraction**:
    - Abstract classes (`Vehicle`) and methods hide complex details and expose essential features.

2. **Encapsulation**:
    - Private fields and public methods ensure data is accessed safely and securely.

3. **Inheritance**:
    - `Car` and `Motorcycle` classes inherit from the `Vehicle` superclass, promoting code reusability.

4. **Polymorphism**:
    - Methods like `calculatePrice` are overridden in subclasses to provide specific implementations for cars and motorcycles.

---

## Class Structure

### 1. **Vehicle (Abstract Class)**
- `String vehicleId`
- `String brand`
- `String model`
- `double basePricePerDay`
- **Abstract Method**: `double calculatePrice(int rentalDays)`

### 2. **Car (Subclass)**
- Inherits from `Vehicle`
- `String fuelType`
- `int numberOfSeats`
- Custom implementation of `calculatePrice`

### 3. **Motorcycle (Subclass)**
- Inherits from `Vehicle`
- Custom implementation of `calculatePrice`

### 4. **Customer**
- `String customerId`
- `String name`

### 5. **Rental**
- `Car car`
- `Customer customer`
- `int days`

### 6. **CarRentalSystem**
- Manages vehicles, customers, and rental transactions
- Handles the user interface and business logic

---

## Setup and Execution

1. **Prerequisites**:
    - Java Development Kit (JDK) 11 or above
    - An IDE like IntelliJ IDEA, Eclipse, or NetBeans

2. **Steps to Run**:
    - Clone this repository:
      ```bash
      git clone https://github.com/shaiful191/G0___A-Comprehensive-Object-Oriented-Car-and-Motorcycle-Rental-Application.git
      ```
    - Open the project in your IDE.
    - Compile and run the `Main` class.

3. **Using the System**:
    - Follow the text-based menu to rent or return vehicles.
    - View rental details and calculate costs dynamically.

---

## Future Enhancements

- **Database Integration**: To store vehicle and customer data persistently.
- **GUI Development**: Implement a graphical user interface for a better user experience.
- **Advanced Pricing Models**: Introduce discounts, peak pricing, and loyalty rewards.
- **Vehicle Maintenance Tracking**: Manage and track the maintenance schedule for each vehicle.

---

## Screenshots

*(You can add screenshots of the application running in the terminal here, if available)*

---

## Contact

For questions, feedback, or collaboration opportunities:

- **Name**: Shaiful Islam
- **Email**: shaifulislam2651@gmail.com

---

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

*Thank you for checking out my project! Feel free to contribute or reach out for any inquiries.* 🚀
