import java.sql.*;
import java.util.*;

public class EnrollmentEntry {

    public void enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (enrollment_id, student_id, student_name, course_name, transaction_status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password)) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, enrollment.getEnrollmentId());
                stmt.setInt(2, enrollment.getStudentId());
                stmt.setString(3, enrollment.getStudentName());
                stmt.setString(4, enrollment.getCourse_name());
                stmt.setBoolean(5, enrollment.isTransaction_status());

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    conn.commit();
                    System.out.println("Enrollment successful.");
                } else {
                    conn.rollback();
                    System.out.println("Enrollment failed. Transaction rolled back.");
                }
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Error during enrollment. Transaction rolled back.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.enrollment_id, s.student_id, s.name AS student_name, e.course_name, e.transaction_status " +
                "FROM enrollments e " +
                "JOIN students_info s ON e.student_id = s.student_id " +
                "WHERE s.student_id = ?";

        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("course_name"),
                        rs.getBoolean("transaction_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    public void deleteEnrollment(int enrollmentId) {
        String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
        try (Connection conn = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollmentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
