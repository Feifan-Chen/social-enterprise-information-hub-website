package com.feifanchen.thirdyearproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Forumpost")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ForumPost implements java.io.Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id", nullable=false)
    private User p_usr;

    @ManyToMany
    @JoinTable(name = "Forumtopic", joinColumns = @JoinColumn(name = "forum_id"), inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private Set<Topic> f_topic = new HashSet<>();

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

    public User getP_usr() {
        return p_usr;
    }

    public void setP_usr(User p_usr) {
        this.p_usr = p_usr;
    }

    public Set<Topic> getTopics(){
        return f_topic;
    }

    public void setTopics(Set<Topic> topics){
        this.f_topic = topics;
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

    public void addWatch(){this.watch++;}
}
