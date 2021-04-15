package core;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "degreeController")
@SessionScoped
public class DegreeController implements Serializable {
    /*
    *  We define holder for form parameters
    * */


    // degree infos
    private Long idDegree;
    private String degreeTitle;
    private int degreeYears;

    private List<Degree> degrees = DAO.getSingletonObjetDAO().
            getDegreeList();
    private List<SelectItem> degreeList;

    // Degree Courses
    private Long idCourse;
    private String courseTitle;
    private String courseDescription;
    private int courseCredits;

    public DegreeController() {
    }



    // Getters and Setters for Degree specifically
    public List<SelectItem> getDegreeList() {
        return degreeList;
    }

    public void setDegreeList(List<SelectItem> degreeList) {
        this.degreeList = degreeList;
    }

    public Long getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(Long idDegree) {
        this.idDegree = idDegree;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public int getDegreeYears() {
        return degreeYears;
    }

    public void setDegreeYears(int degreeYears) {
        this.degreeYears = degreeYears;
    }

    public void populateDegreeSelectItem(){
        degreeList = new ArrayList<SelectItem>();
        for(Degree degree : DAO.getSingletonObjetDAO().
                getDegreeList()){
            degreeList.add(new SelectItem(degree.toString()));
        }

    }

    // Getters and Setters for Course specifically
    public Long getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Long idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    public String navMainMenu() {
        return "main_menu";
    }

    public void cancelEntry() {
        resetDegree();
        resetCourse();
    }

    public void resetDegree() {
        idDegree = 0L;
        degreeTitle = "";
        degreeYears =0 ;
    }

    public void resetCourse() {
        idCourse = 0L;
        courseTitle = "";
        courseCredits = 0;
        courseDescription = "";
    }

    /*
    * */

}
