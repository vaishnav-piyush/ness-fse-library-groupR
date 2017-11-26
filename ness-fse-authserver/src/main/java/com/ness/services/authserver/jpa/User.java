package com.ness.services.authserver.jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLEID")
    private Integer roleId;

    @Column(name = "ACTIVE")
    private boolean active;

    @Column(name = "LOGIN_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_DATE")
    private Date createdDate;

    @Column(name = "UPDATE_DATE")
    private Date updatedDate;

}
