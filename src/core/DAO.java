package core;

import core.utils.Cxo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class DAO implements Serializable {

    private static DAO objetDAO = new DAO();
    private  List<Student> studentList = new ArrayList<>();
    private  List<Course> courseList = new ArrayList<>();
    private List<Degree> degreeList;
    private List<CourseLevel> courseLevels;


    /*@PostConstruct
    public void parametersUploading(){
        Cxo.initConnection();
        Cxo.fetchDegreeFromDB();
    }*/
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

    public boolean checkStudentStatus(Long id){
        Cxo.initConnection();
        Cxo.getStudentListFromDB();
        return Cxo.isStudentIDValid(id) ;
    }

    // Retrieve a student from cache list : studentList
    public Student getStudentFromStudentList(Long id){
        for (Student student :studentList){
            if(student.getIdStudent().compareTo(id) == 0){
                return student;
            }
        }
        return new Student();
    }
    // Insert new student to DB through Cxo utilities
    public void insertNewStudentDB(Student student){
        Cxo.initConnection();
        Cxo.insertData(student);
    }
}
