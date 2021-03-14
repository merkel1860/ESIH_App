package core;


import java.util.ArrayList;
import java.util.List;


public class Student extends Person {
    private Long IdStudent;
    private int year;
    private Address address;
    private List<Materials> materials = new ArrayList<>();

    public Student(String fname, String lname, String gender,
                   int year, Address address, List<Materials> materials) {

        super(fname, lname, gender);
        this.year = year;
        this.address = address;
        this.materials = materials;
    }

    public Student(Person person, Address address) {
        super(person.getFname(), person.getLname(), person.getGender());
        this.address = address;
    }

    public Student(int year, Address address, List<Materials> materials) {
        this.year = year;
        this.address = address;
        this.materials = materials;

    }

    public Student(String fname, String lname, String gender, int year, Address address) {
        super(fname, lname, gender);
        this.year = year;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Materials> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Materials> materials) {
        this.materials = materials;
    }

    public String getFirstname(){
        return this.getFname();
    }

    public String getLastname(){
        return this.getLname();
    }
    public String getStudentGender(){
        return this.getGender();
    }



}

