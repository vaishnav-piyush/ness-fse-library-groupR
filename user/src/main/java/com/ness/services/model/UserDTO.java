package com.ness.services.model;
import com.ness.services.util.ValidUserName;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message = "First name can not be empty")
    @Size(min = 1, max = 15, message = "First name size must be between 1 and 15 characters")
    private String  firstName;

    @NotBlank(message = "Last name can not be empty")
    @Size(min = 1, max = 15,  message = "Last name size must be between 1 and 15 characters")
    private String  lastName;

    private boolean admin;

    @NotBlank(message = "User name can not be empty")
    @Size(min = 4, max = 30, message = "User name size must be between 4 and 30 characters")
    @ValidUserName
    private String userName;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, max = 30, message = "Password size must be between 8 and 30 characters")
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

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
