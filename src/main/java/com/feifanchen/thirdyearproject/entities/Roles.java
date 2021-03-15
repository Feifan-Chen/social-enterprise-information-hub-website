package com.feifanchen.thirdyearproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.*;

@Entity
@Table(name = "Roles")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Roles implements java.io.Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String role_name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch=FetchType.EAGER)
    private List<User> users;

    public Roles(){}

    public Integer getId() {
        return id;
    }

    public String getRole_name(){
        return this.role_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}