public class Enrollment {
    private int enrollmentId;
    private int studentId;
    private String studentName;
    private String course_name;
    private boolean transaction_status;

    public Enrollment(int enrollmentId, int studentId, String studentName, String course_name, boolean transaction_status) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.course_name = course_name;
        this.transaction_status = transaction_status;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse_name() {
        return course_name;
    }

    public boolean isTransaction_status() {
        return transaction_status;
    }
}
