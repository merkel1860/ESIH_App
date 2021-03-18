package core;

public class CourseLevel {
    private  String objectifs;
    private String levelName;
    private String description;
    private int idLevel;


    public CourseLevel() {
    }

    public CourseLevel(String objectifs, String levelName, String description) {
        this.objectifs = objectifs;
        this.levelName = levelName;
        this.description = description;
    }

    public String getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(String objectifs) {
        this.objectifs = objectifs;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }
}
