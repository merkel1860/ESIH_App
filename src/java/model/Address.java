package java.model;


import java.io.Serializable;

public class Address  implements Serializable {
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
}
