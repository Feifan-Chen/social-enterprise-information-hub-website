package com.feifanchen.thirdyearproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.TypeAlias;

import javax.persistence.*;
import java.sql.Timestamp;

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

    //use string to represent the arraylist of topics (id) relates to this podcast
    private String topic;

    private String description;

    private String url;

    private Timestamp add_at;

    private long add_by;


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
}
