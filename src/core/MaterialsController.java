package core;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named(value ="mtsController" )
@SessionScoped

public class MaterialsController implements Serializable {
    private String idStudent;
    private String first_name;
    private  String last_name;
    private String promotion;


    public MaterialsController() {
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

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    // Checking the validity of a given student´s id
    public void checkingStudentStatus(){
        Long idStudentLong = Long.valueOf(idStudent.trim());
        if(DAO.getSingletonObjetDAO().checkStudentStatus(idStudentLong)){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Valid Id Student",null);
            FacesContext.getCurrentInstance().addMessage(null,facesMsg);
            updateFormStudentInfos(idStudentLong);
        }else {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Invalid Id Student",null);
            FacesContext.getCurrentInstance().addMessage(null,facesMsg);
            updateFormStudentInfos();
        }

    }

    private void updateFormStudentInfos(Long idStudentLong) {
        Student studentInfos = DAO.getSingletonObjetDAO().
                getStudentFromStudentList(idStudentLong);
        first_name = studentInfos.getFirstname();
        last_name = studentInfos.getLastname();
        promotion = String.valueOf(studentInfos.getYear());
        idStudent = String.valueOf(studentInfos.getIdStudent());

    }
    private void updateFormStudentInfos(){
        first_name = "";
        last_name = "";
        promotion = "";
        idStudent = "";
    }

    public String navMainMenu(){
        return "main_menu";
    }
}
