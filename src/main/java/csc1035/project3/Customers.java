package csc1035.project3;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cid", updatable = false, nullable = false)
    private int cid;
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Column(name = "phoneExt")
    private String phoneExt;

    public Customers(){

    }

    public Customers(String surname, String email, String phoneExt) {
        //this.cid = cid;
        this.surname = surname;
        this.email = email;
        this.phoneExt = phoneExt;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneExt() {
        return phoneExt;
    }

    public void setPhoneExt(String phoneExt) {
        this.phoneExt = phoneExt;
    }
}
