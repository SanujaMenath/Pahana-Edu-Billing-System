# 🧾 Pahana Edu Billing System

A simple web-based billing system built with **Java Servlets**, **JSP**, and **MySQL**, designed to manage educational institution billing efficiently.

---

## 🚀 Features

-🔐 Secure Login & Role-based Access (Admin & Staff)  
-👩‍💼 Admin: Manage staff accounts 
-👨‍💼 Staff: Manage customers and billing  
-📊 Billing & Payment management  
-📄 Clean MVC architecture for maintainability  
-🌐 Runs on Apache Tomcat  

---

## 🏗️ Tech Stack

| Layer       | Technology              |
|-------------|-------------------------|
| Backend     | Java Servlet (JSP)      |
| Frontend    | HTML, CSS               |
| Database    | MySQL (WAMP)            |
| Server      | Apache Tomcat 10        |
| Build Tool  | Maven                   |

---

## 📋 Prerequisites
 
Before running the application, ensure you have:

☕ Java Development Kit (JDK) 11 or higher  
🐱 Apache Tomcat 10.x server  
🗄️ MySQL 8.0+ database server  
🔧 Maven 3.6+ for build management  
🟨 WAMP/XAMPP (for local MySQL development)  
💻 IDE like IntelliJ IDEA (recommended) with Smart Tomcat plugin  

---

## 📁 Project Structure 

PahanaEduBillingSystem/
├── 📁 src/  
│   └── 📁 main/  
│       ├── 📁 java/  
│       │   └── 📁 com/pahanaedu/      # Java source files (Models, Services, Servlets)  
│       └── 📁 webapp/                 # JSP pages, static files (HTML, CSS, JS)  
├── 📄 pom.xml                         # Maven build configuration  
└── 📄 README.md                       # Project documentation   

---

## ⚙️ Setup & Installation

Follow these steps to run the project locally:
1. Clone the Repository

   - git clone https://github.com/SanujaMenath/Pahana-Edu-Billing-System.git
   - cd Pahana-Edu-Billing-System
   
2. Database Setup

  - Create a MySQL database (e.g., pahana_edu).
  - Import the provided SQL script (pahanaedu_billing.sql).
  - Update DB credentials inside src/main/java/com/pahanaedu/util/DBUtil.java.

3. Build with Maven

   - mvn clean package
   
4. Deploy to Tomcat
   
  - Install Smart Tomcat plugin in IntelliJ IDEA.
  - Configure Tomcat server in plugin settings:
  - Select Tomcat installation directory.
  - Set project deployment directory (src/main/webapp).
  - Set output directory (target/classes).
  - Run the project directly with Run → Smart Tomcat → Run.
   
5. Access the Application
  - Open browser: http://localhost:8080/online-billing-system

---

## 🔄 Version Control Workflow

 - Main branch → stable production-ready code
 - Dev branch → integration and testing before merging
 - Feature branches → e.g., feature/customer, feature/admin-dashboard, etc.
 - Daily commits and merges were done with proper versioning and conflict resolution.

✅ Demonstrates Git workflow, branching strategy, and incremental development.

---

## 🧪 Testing
- The project includes comprehensive testing coverage:
- Test Structure

* Unit Tests: Individual component testing with JUnit 5  
* Test Coverage: 50%+ code coverage maintained  

Test Categories

✅ Authentication & Authorization  
✅ Customer CRUD operations  
✅ Item management functionality  
✅ Billing calculation accuracy  
✅ Database connectivity  

---

## 🚀 Deployment & CI/CD

- Branches were merged into main after testing.  

---

## 📜 License

- This project is developed as part of an academic assignment.
