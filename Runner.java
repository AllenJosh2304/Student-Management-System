import java.util.*;

public class Runner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentEntry se = new StudentEntry();
        CourseView ce = new CourseView();
        EnrollmentEntry ee = new EnrollmentEntry();

        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. View All Courses");
            System.out.println("6. Enroll Student");
            System.out.println("7.View enrollment");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    System.out.println("Select a course from the course table");
                    CourseView.showCourses();
                    System.out.print("Enter the course code: ");
                    int course_code = sc.nextInt();
                    sc.nextLine();
                    String course_name = CourseView.getCourseNameByCode(course_code);
                    System.out.print("Enter the phone number: ");
                    long num = sc.nextLong();
                    se.addStudent(new Student(id, name, email, course_name, course_code, num));
                    break;

                case 2:
                    List<Student> students = se.getAllStudents();
                    for (Student s : students) {
                        System.out.println(s.getStudentId() + " | " + s.getName() + " | " + s.getCourse_name() + " | " + s.getPhone_no());
                    }
                    break;

                case 3:
                    System.out.print("Student ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    Student stu = se.getStudentById(uid);
                    if (stu != null) {
                        System.out.print("New Name: ");
                        stu.setName(sc.nextLine());
                        System.out.print("New Email: ");
                        stu.setEmail(sc.nextLine());
                        System.out.println("Select new course from the course table:");
                        CourseView.showCourses();
                        System.out.print("Enter new course code: ");
                        int newCourseCode = sc.nextInt();
                        sc.nextLine();
                        String newCourseName = CourseView.getCourseNameByCode(newCourseCode);
                        stu.setCourse_code(newCourseCode);
                        stu.setCourse_name(newCourseName);
                        System.out.print("New Phone Number: ");
                        long newPhone = sc.nextLong();
                        stu.setPhone_no(newPhone);
                        se.updateStudent(stu);
                        System.out.println("Student details updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4:
                    System.out.print("Student ID to delete: ");
                    int did = sc.nextInt();
                    se.deleteStudent(did);
                    break;

                case 5:
                    CourseView.showCourses();
                    break;

                case 6:
                    System.out.print("Enrollment ID: ");
                    int eid = sc.nextInt();
                    System.out.print("Student ID: ");
                    int esid = sc.nextInt();
                    sc.nextLine();
                    Student s = se.getStudentById(esid);
                    if (s == null) {
                        System.out.println("Student with ID " + esid + " does not exist. Please register the student first.");
                        break;
                    }
                    String sname = s.getName();
                    int courseCode = s.getCourse_code();
                    String courseName = s.getCourse_name();
                    System.out.print("Transaction Status (1 for true, 0 for false): ");
                    int ts = sc.nextInt();
                    boolean status = (ts == 1);
                    Enrollment enrollment = new Enrollment(eid, esid,sname,courseName, status);
                    ee.enrollStudent(enrollment);
                    System.out.println("Enrollment successful.");
                    break;

                case 7:
                    System.out.print("Enter Student ID to view enrollments: ");
                    int sid = sc.nextInt();
                    List<Enrollment> studentEnrollments = ee.getEnrollmentsByStudentId(sid);

                    if (studentEnrollments.isEmpty()) {
                        System.out.println("No enrollments found for student ID: " + sid);
                    } else {
                        System.out.println("Enrollment ID | Student ID | Student Name | Course Name | Transaction Status");
                        System.out.println("--------------------------------------------------------------------------");
                        for (Enrollment en : studentEnrollments) {
                            System.out.printf("%13d | %10d | %-12s | %-11s | %s\n",
                                    en.getEnrollmentId(),
                                    en.getStudentId(),
                                    en.getStudentName(),
                                    en.getCourse_name(),
                                    en.isTransaction_status() ? "Completed" : "Pending");
                        }
                    }
                    break;
                case 8:
                    System.out.println("Exiting.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
