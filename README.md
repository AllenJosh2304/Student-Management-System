# 🎓 Student Management System (Java + JDBC + MySQL)

A command-line application to manage student records, courses, and enrollments using Java, JDBC, and MySQL.

## 🚀 Features

- Add, view, update, and delete student records
- View all available courses
- Enroll students in courses with transaction status
- View all enrollments by student ID
- JDBC-based MySQL connection with manual transaction control

## 🛠 Technologies Used

- Java 
- JDBC (Java Database Connectivity)
- MySQL
- IntelliJ IDEA

## 🗃 Database Structure

### Table: `students_info`
| Column        | Type         |
|---------------|--------------|
| student_id    | INT (PK)     |
| name          | VARCHAR      |
| email         | VARCHAR      |
| course_name   | VARCHAR      |
| course_code   | INT          |
| phone_no      | BIGINT       |

### Table: `courses_info`
| Column        | Type         |
|---------------|--------------|
| course_code   | INT (PK)     |
| course_name   | VARCHAR      |

### Table: `enrollments`
| Column             | Type         |
|--------------------|--------------|
| enrollment_id      | INT (PK)     |
| student_id         | INT (FK)     |
| student_name       | VARCHAR      |
| course_name        | VARCHAR (FK) |
| transaction_status | BOOLEAN      |

## 📦 How to Run

1. Clone the repository:
    ```bash
    git clone https://github.com/AllenJosh2304/student-management-system
    cd student-management-system
    ```

2. Set up the MySQL database and tables as per structure above.

3. Open project in IntelliJ or any IDE with JDK support.

4. Update DB credentials in the code (`url`, `username`, `password`).

5. Run the `Runner.java` class.

## 👨‍💻 Author

- **Allen Joshua** – [GitHub](https://github.com/AllenJosh2304) | [LinkedIn](https://www.linkedin.com/in/allenjosh2304)

## 🌟 Feedback

Star ⭐ the repo if you like it, and feel free to fork or contribute!
