package csc1035.project3;

public class Customer {
    private int cid;
    private String surname;
    private String email;
    private String phoneExt;

    public Customer(int cid, String surname, String email, String phoneExt) {
        this.cid = cid;
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
