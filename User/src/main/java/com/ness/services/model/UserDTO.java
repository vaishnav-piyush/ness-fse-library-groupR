package com.ness.services.model;

public class UserDTO {

    private String  firstName;
    private String  lastName;
    private Boolean admin;
    private String userName;
    private String password;


    public UserDTO() {

    }
    public UserDTO(String firstName, String lastName,
            boolean isAdmin, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = isAdmin;
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
