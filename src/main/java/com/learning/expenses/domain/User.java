package com.learning.expenses.domain;

import java.util.Date;

public class User {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date createdAt;
    private Date lastLogin;

    public User(Integer userId, String firstName, String lastName, String email, String password, Date createdAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
