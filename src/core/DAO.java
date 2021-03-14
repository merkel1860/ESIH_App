package core;

import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static DAO objetDAO = new DAO();
    private  List<Student> studentList = new ArrayList<>();
    private  List<Course> courseList = new ArrayList<>();

    private DAO() {
    }

    public static DAO getSingletonObjetDAO() {
        return objetDAO;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }
}
