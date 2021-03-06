
package core;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named(value = "enrollController")
@SessionScoped

public class StudentEnrollController implements Serializable {

    private static final long serialVersionUID = 5443351151396868724L;
    private String first_name;
    private String last_name;
    private String gender;
    private String email;
    private int promotion;
    private int yob;

    public StudentEnrollController() {
    }

    public StudentEnrollController(String first_name, String last_name,
                                   String gender, String email, int promotion) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.promotion = promotion;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public void addAStudent(){
        Student student = new Student(first_name, last_name,gender, yob, new Address(email));
        DAO.getSingletonObjetDAO().getStudentList().add(student);
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                "You have successfully added new user : "+student.toString(),null);
        FacesContext.getCurrentInstance().addMessage(null,facesMsg);
        DAO.getSingletonObjetDAO().insertNewStudentDB(student);
        resetParameterInForm();
    }

    public void cancelEntry(){
        resetParameterInForm();
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Wrong values, review entered values",null);
        FacesContext.getCurrentInstance().addMessage(null,facesMsg);
    }

    public String navMainMenu(){
        return "main_menu";
    }

    private void resetParameterInForm() {
        first_name = "";
        last_name = "";
        gender = "";
        promotion = 0;
        email = "";
    }


}
