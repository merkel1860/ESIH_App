package core;

import java.util.List;
import java.util.logging.Level;

public class Degree {
    private String degreeName;
    private int length;
    private  int idDegree;
    private List<DegreeLevel> levelList;


    public Degree(String degreeName, int length, int idDegree) {
        this.degreeName = degreeName;
        this.length = length;
        this.idDegree = idDegree;
    }


    public Degree() {
    }

    public Degree(String degreeTitle, int degreeYears) {
        degreeName = degreeTitle;
        length = degreeYears;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(int idDegree) {
        this.idDegree = idDegree;
    }

    public List<DegreeLevel> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<DegreeLevel> levelList) {
        this.levelList = levelList;
    }

    @Override
    public String toString() {
        return degreeName + " : " + idDegree ;
    }
}
