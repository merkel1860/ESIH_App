package core;

import com.sun.javafx.geom.transform.BaseTransform;
import core.utils.Cxo;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private static DAO objetDAO = new DAO();
    private  List<Student> studentList = new ArrayList<>();
    private  List<Course> courseList = new ArrayList<>();
    private List<Degree> degreeList;
    private List<CourseLevel> courseLevels;

    private DAO() {
        //Cxo.pickBaseParameter("Level");
        //courseLevels = Cxo.fetchCourseLevelFromDB();
       // Cxo.pickBaseParameter("Degree");
       // degreeList =  Cxo.fetchDegreeFromDB();
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

    public boolean checkStudentStatus(String id){
        boolean isStudentIn = false;
        for(Student student : studentList){
            if(Long.valueOf(id.trim()).compareTo(student.getIdStudent()) ==0){
                isStudentIn = true;
            }
        }
        return isStudentIn;
    }

    public void insertNewStudentDB(Student student){
        Cxo.initConnection();
        Cxo.insertData(student);
    }
}
