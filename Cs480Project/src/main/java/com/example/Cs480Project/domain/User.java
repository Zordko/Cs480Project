package com.example.Cs480Project.domain;

import javax.persistence.*;

@Entity
@Table(name = "logintable")

public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)

   private String username;
   private String password;
public User(){

}
    public User( String username, String password ) {
        this.username = username;
        this.password = password;

    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
