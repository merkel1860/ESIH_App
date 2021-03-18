package core;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "courseController")
@SessionScoped

public class CourseEnrollController  implements Serializable {

    private String title;
    private int credits;
    private String description;
    private int idCourse;
    private int idDegree;


    /* --------------------------------------------------------------------
    *       CONSTRUCTOR SECTION
    *
    * */ //----------------------------------------------------------------
    public CourseEnrollController() {
    }

    public CourseEnrollController(String title, int credits, String description) {
        this.title = title;
        this.credits = credits;
        this.description = description;
    }
    /* ----------------------------------------------------------------------
    *
    *   GETTERS AND SETTER SECTION
    *
    *
    * */ // -----------------------------------------------------------------


    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    /* -----------------------------------------------------
    *
    *
    * */ //------------------------------------------------
    public void addNewCourse(){
        Course c = new Course(title, description, credits);
    }

    public void cancelEntry(){
        title = "";
        description = "";
        credits = 0;
    }
}
