package com.feifanchen.thirdyearproject.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "Youtube")
public class YouTube {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String thumbnail;

    //use string to represent the arraylist of topics (id) relates to this podcast
    private String topic;

    private String description;

    private String url;

    private Timestamp add_at;

    private long add_by;

    private Timestamp time;

    public YouTube(){

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

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public long getAdd_by(){
        return add_by;
    }

    public void setAdd_by(long id){
        this.add_by = id;
    }

    public Timestamp getAdd_at() {
        return this.add_at;
    }

    public void setAdd_at(Timestamp timestamp) {
        this.add_at = timestamp;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        this.time = timestamp;
    }

}
