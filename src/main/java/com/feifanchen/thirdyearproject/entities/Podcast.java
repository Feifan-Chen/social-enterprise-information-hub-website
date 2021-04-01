package com.feifanchen.thirdyearproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Podcast")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Podcast implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;


    @ManyToMany
    @JoinTable(name = "Podcasttopic", joinColumns = @JoinColumn(name = "podcast_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> p_topic = new HashSet<>();

    private String description;

    private String url;

    private Timestamp add_at;

    private String add_by;

    private Date time;


    public Podcast(){

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

    public Set<Topic> getTopic(){
        return p_topic;
    }

    public void setTopic(Set<Topic> topics){
        this.p_topic = topics;
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

    public void setTime(Date timestamp) {
        this.time = timestamp;
    }
}
