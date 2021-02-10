package com.feifanchen.thirdyearproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    private long user_id;

    private String user_name;

    private String password;

    private int admin;

    public User(){
    }

    public long getUser_id(){return user_id;}

    public void setUser_id(long user_id) {this.user_id = user_id;}

    public String getUser_name() {return user_name;}

    public void setUser_name(String user_name){ this.user_name = user_name;}

    public boolean checkAdmin(){
        if(admin == 1)
            return true;
        else
            return false;
    }

    public void setAdmin(int i){ this.admin = i;}
}
