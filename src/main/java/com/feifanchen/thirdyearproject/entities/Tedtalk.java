package com.feifanchen.thirdyearproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Tedtalk")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tedtalk implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String thumbnail;

    @ManyToMany
    @JoinTable(name = "Tedtopic", joinColumns = @JoinColumn(name = "ted_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> t_topic = new HashSet<>();

    private String description;

    private String url;

    private Timestamp add_at;

    private String add_by;

    private Date time;


    public Tedtalk(){

    }

    public long getId(){return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public Set<Topic> getTopic(){
        return t_topic;
    }

    public void setTopic(Set<Topic> topics){
        this.t_topic = topics;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getAdd_by(){
        return add_by;
    }

    public void setAdd_by(String id){
        this.add_by = id;
    }

    public Timestamp getAdd_at() {
        return this.add_at;
    }

    public void setAdd_at(Timestamp timestamp) {
        this.add_at = timestamp;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
