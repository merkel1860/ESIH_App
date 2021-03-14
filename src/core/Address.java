package core;


public class Address  {
    String email;

    public Address(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress(){
        return  new Address(this.email);
    }
}
