package core;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
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

    private List<Degree> degrees;
    private List<SelectItem> degreeList;
    private final String editColumn;
    private boolean editable;

    // Degree Courses
    private Long idCourse;
    private String courseTitle;
    private String courseDescription;
    private int courseCredits;

    // Level Infos
    private String selectedLevel;
    private boolean editLevel;

    // Sub form for gathering information on new Degree
    private boolean subFormStatus = false;

    // Default Constructor
    public DegreeController() {
        editColumn = "New Degree";
        editable = false;
        editLevel = false;
    }


    @PostConstruct
    private void testFetchingDB() {
        DAO.getSingletonObjetDAO().fetchDegreeList();
        degrees = DAO.getSingletonObjetDAO().
                getDegreeList();
        populateDegreeSelectItem();
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

    // Populating SelectItem with Degree programs
    // By converting List<Degree>  to List<SelectItem>
    public void populateDegreeSelectItem() {
        degreeList = new ArrayList<SelectItem>();
        if (degrees.size() > 0) {
            for (Degree degree : degrees) {
                degreeList.add(new SelectItem(degree.toString()));
            }
        } else {
            degreeList.add(new SelectItem("Please enter new degrees"));
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
        editLevel = false;
        return "main_menu";
    }

    public void cancelEntry() {
        resetDegree();
        resetCourse();
    }

    public void resetDegree() {
        idDegree = 0L;
        degreeTitle = "";
        degreeYears = 0;
    }

    public void resetCourse() {
        idCourse = 0L;
        courseTitle = "";
        courseCredits = 0;
        courseDescription = "";
    }

    public boolean isactivatedSubForm() {
        this.subFormStatus = !(subFormStatus);
        System.out.println("Status : " + subFormStatus);
        return subFormStatus;

    }

    public String getEditColumn() {
        return editColumn;
    }

    public Boolean getEditable() {
        return editable;
    }

    public void setEditable(Boolean editable) {
        this.editable = editable;
        if (editable) {
            editLevel = false;
        }

    }

    // Sub Form for adding more degree program on demand
    public void saveNewDegree() {
        boolean statusSavingProcess = false;
        Degree degree = new Degree(degreeTitle.toUpperCase(), degreeYears);

        statusSavingProcess = DAO.getSingletonObjetDAO().
                insertNewDegree(degree);
        degree.setIdDegree(DAO.getSingletonObjetDAO().
                retrieveDegreeID(degree.getDegreeName()));
        DAO.getSingletonObjetDAO().getDegreeList().add(degree);
        UpdateInterfaceMessage(statusSavingProcess, degreeTitle);
        populateDegreeSelectItem();
        cancelNewDegree();
        addWillBeUpdatedComponent("degreeSelectOneMenu");

    }

    // Updating View component by id
    public static void addWillBeUpdatedComponent(final String componentId) {
        FacesContext.getCurrentInstance().getPartialViewContext()
                .getRenderIds().add(componentId);
    }

    // Flexible updateInterfaceMessage call after each request
    // to inform end-user of operation's completeness status
    private void UpdateInterfaceMessage(boolean statusSavingProcess,
                                        String scenario) {
        if (statusSavingProcess) {
            FacesMessage facesMsg = new FacesMessage(
                    FacesMessage.SEVERITY_INFO,
                    "You have successfully added : "
                            + scenario, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        } else {
            FacesMessage facesMsg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error while adding  : "
                            + scenario, null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
        }
    }

    // Sub Form clearing
    public void cancelNewDegree() {
        degreeTitle = "";
        degreeYears = 0;
        editable = false;
    }

    public String getSelectedLevel() {
        return selectedLevel;
    }

    public void setSelectedLevel(String selectedLevel) {
        this.selectedLevel = selectedLevel;
    }

    public void listenerDegreeSelectOneMenu(ValueChangeEvent changeEvent) {
        editLevel = true;
    }

    public Boolean getEditLevel() {
        return editLevel;
    }

    public void setEditLevel(boolean editLevel) {
        this.editLevel = editLevel;
    }
    /*
     * */

}
