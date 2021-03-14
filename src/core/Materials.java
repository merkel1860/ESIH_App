package core;

import java.util.List;

public class Materials extends Course{
     private int idMaterial;
     private float record;
     private Homework listOfHomework;
     private List<Examen> ListOfExamen;

     public Materials(int title, int idDegree) {
          super(title, idDegree);
     }



     public float getRecord() {
          return record;
     }
}
