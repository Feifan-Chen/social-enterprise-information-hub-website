package com.feifanchen.thirdyearproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String topic;

    private String description;

    private String thumbnail;

    private int ticket;

    private String location;

    private String holder;

    private String url;

    private Date date;

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

    public String getTopic(){
        return topic;
    }

    public void setTopic(String topics){
        this.topic = topics;
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

    public Date getDate() {return date;}

    public void setDate(Date date) {this.date = date;}

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        this.time = timestamp;
    }


}
