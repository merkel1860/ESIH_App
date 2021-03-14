package core;

public class Course {
    private int title;
    private int IdDegree;
    private int IdCourse;

    public Course(int title, int idDegree) {
        this.title = title;
        IdDegree = idDegree;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
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
}
