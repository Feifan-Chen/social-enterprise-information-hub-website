package com.feifanchen.thirdyearproject.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Youtube")
@DynamicInsert(true)
@DynamicUpdate(true)
public class YouTubeVideo implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String thumbnail;

    //use string to represent the arraylist of topics (id) relates to this podcast
    @JsonIgnore
    @ManyToMany(mappedBy = "youtube", fetch=FetchType.EAGER)
    private List<Topic> topic;

    private String description;

    private String url;

    private Timestamp add_at;

    private String add_by;

    private Date time;

    public YouTubeVideo(){

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

    public List<Topic> getTopic(){
        return topic;
    }

    public void setTopic(List<Topic> topics){
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
