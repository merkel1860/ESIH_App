package core;

import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class DegreeLevel  {
    private String levelName;
    private int idLevel;
    private String descrip;
    private String objectives;

    public DegreeLevel() {
        super();
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }


}
