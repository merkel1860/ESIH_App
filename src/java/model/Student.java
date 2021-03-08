package java.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
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

