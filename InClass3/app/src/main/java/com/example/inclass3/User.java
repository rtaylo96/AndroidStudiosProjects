package com.example.inclass3;

import java.io.Serializable;

public class User implements Serializable {
    String name, email, id, dept;

    public User(String name, String email, String id, String dept) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.dept = dept;
    }

    public User() {

    }
}
