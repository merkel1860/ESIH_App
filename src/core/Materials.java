package core;

import java.util.List;

public class Materials extends Course{
     private int idMaterial;
     private float record;
     private Homework listOfHomework;
     private List<Examen> ListOfExamen;

     public Materials(String title, int idDegree) {
          super(title, idDegree);
     }



     public float getRecord() {
          return record;
     }
}
