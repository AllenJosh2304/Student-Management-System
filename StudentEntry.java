import java.sql.*;
import java.util.*;

public class StudentEntry {

    public void addStudent(Student student) {
        String sql = "INSERT INTO students_info (student_id, name, email, course_name, course_code, phone_no) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password)) {
            conn.setAutoCommit(false); // Turn off auto-commit
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, student.getStudentId());
                stmt.setString(2, student.getName());
                stmt.setString(3, student.getEmail());
                stmt.setString(4, student.getCourse_name());
                stmt.setInt(5, student.getCourse_code());
                stmt.setLong(6, student.getPhone_no());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    conn.commit();
                    System.out.println("Student added successfully.");
                } else {
                    conn.rollback(); // Rollback in case of failure
                    System.out.println("Failed to add student. Transaction rolled back.");
                }
            } catch (SQLException e) {
                conn.rollback(); // Rollback on error
                System.out.println("Exception occurred. Transaction rolled back.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students_info WHERE student_id = ?";
        Student student = null;
        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course_name"),
                        rs.getInt("course_code"),
                        rs.getLong("phone_no")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students_info";
        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("course_name"),
                        rs.getInt("course_code"),
                        rs.getLong("phone_no")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void updateStudent(Student student) {
        String sql = "UPDATE students_info SET name = ?, email = ?, course_name = ?, course_code = ?, phone_no = ? WHERE student_id = ?";

        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password)) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, student.getName());
                stmt.setString(2, student.getEmail());
                stmt.setString(3, student.getCourse_name());
                stmt.setInt(4, student.getCourse_code());
                stmt.setLong(5, student.getPhone_no());
                stmt.setInt(6, student.getStudentId());

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    conn.commit();
                    System.out.println("Student updated successfully.");
                } else {
                    conn.rollback();
                    System.out.println("Update failed. Transaction rolled back.");
                }
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error during student update. Transaction rolled back.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students_info WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
