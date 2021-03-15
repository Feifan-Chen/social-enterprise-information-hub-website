package com.feifanchen.thirdyearproject.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Forumpost")
public class ForumPost {
    @Id
    @GeneratedValue
    private long id;

    private String title;

    private long usr_id;

    private String topics;

    private String content;

    @OneToMany(targetEntity=ForumComment.class, mappedBy="post", fetch = FetchType.EAGER)
    private Set<ForumComment> comments;

    private Timestamp create_time;

    private int watch;

    private int report;

    public ForumPost(){

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

    public long getUsr_id(){return usr_id;}

    public void setUsr_id(long usr_id) {
        this.usr_id = usr_id;
    }

    public String getTopics(){
        return topics;
    }

    public void setTopics(String topics){
        this.topics = topics;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Set<ForumComment>  getComments(){
        Set<ForumComment> res = new HashSet<>();
        for(ForumComment c : comments){
            if(c.getReport() != 1)
                res.add(c);
        }
        return res;
    }

    public void setComments(Set<ForumComment>  comments){
        this.comments = comments;
    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getWatch(){return watch;}

    public void setWatch(int watch){this.watch = watch;}

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }
}
