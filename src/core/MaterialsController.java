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


    public MaterialsController() {
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }
    public void checkingStudentStatus(){
        Long idStudentLong = Long.valueOf(idStudent.trim());
        if(DAO.getSingletonObjetDAO().checkStudentStatus(idStudentLong)){
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Valid Id Student",null);
            FacesContext.getCurrentInstance().addMessage(null,facesMsg);
        }else {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Invalid Id Student",null);
            FacesContext.getCurrentInstance().addMessage(null,facesMsg);
        }

    }

    public String navMainMenu(){
        return "main_menu";
    }
}
