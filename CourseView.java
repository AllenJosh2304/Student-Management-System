import java.sql.*;

public class CourseView {

    public static void showCourses() {
        String query = "SELECT * FROM courses_info";

        try (Connection con = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            System.out.println("\nCourse Code | Course Name");
            System.out.println("-------------------------");

            while (rs.next()) {
                int code = rs.getInt("course_code");
                String name = rs.getString("course_name");
                System.out.println(code + "         | " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getCourseNameByCode(int courseCode) {
        String query = "SELECT course_name FROM courses_info WHERE course_code = ?";

        try (Connection con = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.password);
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, courseCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("course_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // not found
    }
}
