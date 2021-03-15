package com.feifanchen.thirdyearproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Forumcomment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ForumComment implements java.io.Serializable{
    @Id
    @GeneratedValue
    private long id;

    private long usr_id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable=false)
    private ForumPost post;

    private String content;

    private Timestamp create_time;

    private int report;

    public ForumComment(){

    }

    public long getId(){return id;}

    public void setId(long id) {
        this.id = id;
    }

    public long getUsr_id(){return usr_id;}

    public void setUsr_id(long usr_id) {
        this.usr_id = usr_id;
    }

    public ForumPost getPost(){
        return post;
    }

    public void setPost(ForumPost post){
        this.post = post;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public Timestamp getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getReport(){return report;}

    public void setReport(int report) {
        this.report = report;
    }
}
