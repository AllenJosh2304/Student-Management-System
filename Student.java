public class Student {
    private int studentId;
    private String name;
    private String email;
    private String course_name;
    private int course_code;
    private long phone_no;

    public Student(int studentId, String name, String email, String course_name,int course_code,long phone_no) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.course_name = course_name;
        this.course_code = course_code;
        this.phone_no = phone_no;
    }

    public int getStudentId() {
        return studentId;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getCourse_code() {
        return course_code;
    }

    public long getPhone_no() {
        return phone_no;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_code(int course_code) {
        this.course_code = course_code;
    }

    public void setPhone_no(long phone_no) {
        this.phone_no = phone_no;
    }
}
