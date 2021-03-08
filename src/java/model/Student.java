package java.model;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "studentController")
@SessionScoped

public class Student extends Person implements Serializable {
    private Long IdStudent;
    private int year;
    private Materials listOfMaterials;
    private Address studentAdress;
    public Address adress;
    private List<Materials> materials = new ArrayList<Materials>();

    public Student(String fname, String lname, String gender, int year, Address adress) {
        super(fname, lname, gender);
        this.year = year;
        this.adress = adress;
        this.IdStudent = super.getIdPerson();
    }

    public Student() {
        super();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return  super.toString()+" year : "+this.year;
    }
}

