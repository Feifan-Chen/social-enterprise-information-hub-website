package com.feifanchen.thirdyearproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Event")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Event implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "Eventtopic", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> e_topic = new HashSet<>();

    private String description;

    private String thumbnail;

    private int ticket;

    private String location;

    private String holder;

    private String url;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private Timestamp time;

    public Event(){

    }

    public long getId(){return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){ this.name = name; }

    public Set<Topic> getTopic(){
        return e_topic;
    }

    public void setTopic(Set<Topic> topics){
        this.e_topic = topics;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getThumbnail(){
        return thumbnail;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public int getTicket(){return ticket;}

    public void setTicket(int ticket){this.ticket = ticket;}

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getHolder(){
        return holder;
    }

    public void setHolder(String holder){
        this.holder = holder;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        this.time = timestamp;
    }

    public boolean happenUpcoming() {
        return date.isAfter(LocalDate.now());
    }

    public boolean happenToday() {
        return date.isEqual(LocalDate.now());
    }
}
