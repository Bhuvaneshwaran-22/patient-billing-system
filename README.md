# Patient Appointment & Billing System 🏥

A comprehensive Java console application designed to streamline healthcare administration—from patient registration through to payment processing.

## Overview

This system was developed as part of a Java programming assignment with a focus on solving real-world problems in healthcare management. Instead of building a theoretical application, I wanted to create something that addresses actual challenges faced by clinics and small healthcare facilities: managing patient records, coordinating doctor schedules, tracking consultations, and processing payments efficiently.

The application demonstrates object-oriented programming principles while providing practical functionality for healthcare administrative workflows.

## Core Features

### 👥 Patient & Doctor Management
- **Patient Registration**: Capture patient details with auto-generated unique identifiers
- **Doctor Profiles**: Store doctor information including specialization and availability
- **Contact Management**: Maintain up-to-date contact information for all parties

### 📅 Appointment Scheduling
- **Real-time Slot Availability**: View and book available appointment slots
- **Conflict Prevention**: Automatic validation prevents double-booking
- **Status Tracking**: Monitor appointment completion status

### 💊 Consultation Recording
- **Detailed Documentation**: Record consultation outcomes and fees
- **Prescription Management**: Add multiple prescribed items with individual pricing
- **Consultation History**: Link consultations back to specific appointments

### 🧾 Billing & Invoicing
- **Automated Calculations**: Generate invoices with consultation fees, prescriptions, and tax
- **Tax Integration**: Applies configurable tax rate (currently set at 10%)
- **Invoice Status**: Track whether invoices are open or settled

### 💳 Payment Processing
- **Payment Validation**: Ensures payment amounts match invoice totals
- **Settlement Tracking**: Automatic invoice closure upon successful payment
- **Payment History**: Maintain records of all transactions

## System Architecture

The application follows object-oriented design principles with clear separation of concerns:

```
┌─────────┐     ┌────────────┐     ┌──────────────┐     ┌─────────┐     ┌─────────┐
│ Patient │────▶│ Appointment│────▶│ Consultation │────▶│ Invoice │────▶│ Payment │
└─────────┘     └────────────┘     └──────────────┘     └─────────┘     └─────────┘
                      │
                      ▼
                ┌────────┐
                │ Doctor │
                └────────┘
```

### Key Components

| Component | Responsibility |
|-----------|----------------|
| **Patient** | Manages patient demographic and contact information |
| **Doctor** | Handles doctor profiles, specializations, and time slot availability |
| **Appointment** | Links patients with doctors for specific time slots |
| **Prescription** | Contains prescribed items with associated costs |
| **Consultation** | Records consultation details, fees, and prescriptions |
| **Invoice** | Calculates total billing including taxes |
| **Payment** | Processes payments and settles invoices |

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Terminal/Command Prompt access
- Basic familiarity with command-line operations

### Installation

1. **Clone the repository:**
```bash
git clone https://github.com/Bhuvaneshwaran-22/patient-billing-system.git
cd patient-billing-system
```

2. **Compile the application:**
```bash
javac PatientAppointmentBillingSystem.java
```

3. **Run the application:**
```bash
java PatientAppointmentBillingSystem
```

The application will launch with an interactive menu interface.

## User Guide

### Main Menu

Upon launching, you'll see the following options:

```
--- Menu ---
1. Add Patient
2. Add Doctor
3. Schedule Appointment
4. Record Consultation
5. Generate Invoice
6. Record Payment
7. List Appointments
8. Exit
```

### Complete Workflow Example

Here's a typical use case demonstrating the full patient journey:

#### 1. Register a New Patient
```
Choose option: 1
Enter patient name: Sarah Johnson
Enter patient contact: +1-555-0123
→ Patient added with ID: 1
```

#### 2. Add a Doctor to the System
```
Choose option: 2
Enter doctor name: Dr. Michael Chen
Enter specialization: Cardiology
Enter available slots: 9AM-10AM,10AM-11AM,2PM-3PM
→ Doctor added with ID: 1
```

#### 3. Schedule an Appointment
```
Choose option: 3
Enter patient ID: 1
Enter doctor ID: 1
Available slots: [9AM-10AM, 10AM-11AM, 2PM-3PM]
Choose slot: 9AM-10AM
→ Appointment scheduled with ID: 1 at slot 9AM-10AM
```

#### 4. Record the Consultation
```
Choose option: 4
Enter appointment ID: 1
Enter consultation fee: 150.00
Add prescribed items (format: name,price), empty line to stop:
Item: Lisinopril 10mg,25.00
Item: Blood pressure monitor,75.00
Item: [Press Enter]
→ Consultation recorded
```

#### 5. Generate Invoice
```
Choose option: 5
Enter consultation ID: 1
→ Invoice generated with ID: 1, Total: 275.00
   (Calculation: $150 + $25 + $75 = $250 + 10% tax = $275.00)
```

#### 6. Process Payment
```
Choose option: 6
Enter invoice ID: 1
Enter payment amount: 275.00
→ Payment settled and invoice closed
```

## Technical Implementation

### Technology Stack
- **Language**: Java (JDK 8+)
- **Data Structures**: 
  - `HashMap` for efficient entity storage and retrieval
  - `HashSet` for managing doctor appointment slots
  - `ArrayList` for prescription item collections
- **I/O**: Scanner class for console input/output
- **Design Pattern**: Object-Oriented Programming with encapsulation

### Data Validation & Business Logic

The system includes several built-in validations:

✅ **Appointment Booking**: Verifies both patient and doctor exist before scheduling  
✅ **Slot Management**: Prevents booking unavailable time slots  
✅ **Consultation Recording**: Ensures appointments aren't marked complete multiple times  
✅ **Payment Processing**: Validates payment amount matches invoice total  
✅ **Invoice Closure**: Prevents payment processing on already-settled invoices

### Auto-ID Generation

All entities utilize static counters for automatic ID assignment:
- Ensures uniqueness within each entity type
- Eliminates manual ID management
- Provides simple integer-based lookups

### Invoice Calculation Formula

```
Subtotal = Consultation Fee + Sum(Prescription Items)
Tax = Subtotal × 0.10
Total = Subtotal + Tax
```

## Current Limitations

I believe in transparency about what the system can and cannot do in its current state:

**Data Persistence**: The system uses in-memory storage. Data is lost when the application closes. This makes it suitable for demonstrations and short-term use but not for production environments without modification.

**User Interface**: Console-based interaction is functional but may not suit all users. A graphical interface would improve accessibility.

**Edit Capabilities**: Once data is entered, there's no built-in way to edit or delete records. You'd need to restart the application to clear data.

**Concurrency**: Designed as a single-user application. Multiple simultaneous users would require significant architectural changes.

## Future Enhancements

I'm considering these improvements for future versions:

### Short-term Goals
- 💾 **Database Integration**: PostgreSQL or MySQL for persistent storage
- ✏️ **CRUD Operations**: Full create, read, update, and delete capabilities
- 📋 **Enhanced Reporting**: View patient history, doctor schedules, and financial summaries
- ❌ **Appointment Cancellation**: Allow rescheduling and cancellation with slot release

### Long-term Vision
- 🖥️ **GUI Development**: JavaFX or web-based interface
- 🔐 **Authentication System**: Role-based access control (admin, doctor, receptionist)
- 📧 **Notification System**: Email/SMS confirmations for appointments
- 📊 **Analytics Dashboard**: Insights on patient flow, revenue, and doctor utilization
- 🌐 **Multi-location Support**: Manage multiple clinic branches
- 📱 **Mobile Compatibility**: Responsive design or native mobile apps

## Contributing

This project welcomes contributions from the community. Whether you're fixing bugs, improving documentation, or proposing new features, your input is valued.

### How to Contribute

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/YourFeature`)
3. **Commit** your changes (`git commit -m 'Add helpful feature'`)
4. **Push** to the branch (`git push origin feature/YourFeature`)
5. **Open** a Pull Request

### Contribution Guidelines

- Write clear, commented code
- Follow existing code style and conventions
- Test your changes thoroughly
- Update documentation as needed
- Be respectful in all interactions

## Use Cases

This application is particularly well-suited for:

📚 **Educational Purposes**: Learning Java, OOP concepts, and system design  
🏥 **Small Clinics**: Lightweight solution for practices just getting started with digital systems  
🧪 **Prototyping**: Foundation for more comprehensive healthcare management systems  
👨‍💻 **Portfolio Projects**: Demonstrates practical programming skills to potential employers

## Technical Support & Contact

For questions, bug reports, or collaboration opportunities:

**Developer**: Bhuvaneshwaran  
**GitHub**: [@Bhuvaneshwaran-22](https://github.com/Bhuvaneshwaran-22)  
**Repository**: [patient-billing-system](https://github.com/Bhuvaneshwaran-22/patient-billing-system)

Feel free to open an issue on GitHub for bug reports or feature requests. I typically respond within 24-48 hours.

## Acknowledgments

This project was developed as an academic assignment but evolved into something more comprehensive. Special thanks to:

- My instructors for providing the initial challenge
- The Java community for excellent documentation and resources
- Fellow developers who've provided feedback and suggestions

## License

This project is made available for educational and learning purposes. Feel free to use, modify, and build upon it. If you create something interesting based on this work, I'd love to hear about it!

## Final Thoughts

While this application started as a classroom assignment, I've tried to build something that demonstrates how software can solve real problems in healthcare administration. It's not enterprise-ready out of the box, but it provides a solid foundation for understanding healthcare workflows and practicing Java development.

The healthcare industry increasingly relies on software to manage operations efficiently. This project represents a small step in understanding those systems—their requirements, challenges, and potential solutions.

Whether you're a student learning Java, a developer exploring healthcare IT, or someone curious about medical billing systems, I hope this project provides value and insight.

---

**Built with Java ☕**  
*Version 1.0 • Last Updated: September 2025*