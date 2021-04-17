package core;

import core.utils.Cxo;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@ApplicationScoped
@Named(value ="DAO")
@SessionScoped

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
        Cxo.fetchStudentListFromDB();
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

    // Retrieve all degree program from DB
    public void fetchDegreeList(){
        Cxo.initConnection();
        degreeList = Cxo.fetchDegreeFromDB();
    }

    public List<Degree> getDegreeList() {
        return degreeList;
    }

    public boolean insertNewDegree(Degree degree) {
        boolean statusInsertion = false;
        Cxo.initConnection();
        statusInsertion = Cxo.insertData(degree);
        return statusInsertion;
    }

    public int retrieveDegreeID(String degreeName) {
        Cxo.initConnection();
        return Cxo.fetchDegreeInfo(degreeName);
    }
}
