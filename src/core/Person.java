package core;

import java.util.ArrayList;
import java.util.List;


public class Person {
    // the number of student
    private static List<Long> personEffective = new ArrayList<>();
    // maximum of managed students
    private static final int permittedStudentRange = 1000;

    private String fname;
    private String lname;
    private String gender;
    private Long idPerson;

    public Person(String fname, String lname, String gender) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        idPerson = pseudoRandomGenerator();
    }

    public Person() {
    }

    private long pseudoRandomGenerator() {
        long index;
        do {
            index = Math.round(Math.random() * permittedStudentRange);
        } while (personEffective.contains(index));
        personEffective.add(index);
        return index;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getIdPerson() {
        return idPerson;
    }

    @Override
    public String toString() {
        return lname+" "+fname;
    }

    public Person getPerson(){
        return  new Person(this.getFname(),this.getLname(),this.getGender());
    }
}

