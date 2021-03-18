package core;

public class Course {
    private String title;
    private int IdDegree;
    private int IdCourse;
    private String description;
    private int credits;


    public Course() {
    }

    public Course(String title, int idDegree) {
        this.title = title;
        IdDegree = idDegree;
    }

    public Course(String title, String description, int credits) {
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIdDegree() {
        return IdDegree;
    }

    public void setIdDegree(int idDegree) {
        IdDegree = idDegree;
    }

    public int getIdCourse() {
        return IdCourse;
    }

    public void setIdCourse(int idCourse) {
        IdCourse = idCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
