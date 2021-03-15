package com.feifanchen.thirdyearproject.entities;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;

    @Column(nullable = false, unique = true)
    private String user_name;

    @Column(nullable = false, length = 64)
    private String password;

    private int admin;

    public User(){
    }

    public long getUser_id(){return user_id;}

    public void setUser_id(long user_id) {this.user_id = user_id;}

    public String getUser_name() {return user_name;}

    public void setUser_name(String user_name){ this.user_name = user_name;}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkAdmin(){
        if(admin == 1)
            return true;
        else
            return false;
    }

    public void setAdmin(int i){ this.admin = i;}
}
