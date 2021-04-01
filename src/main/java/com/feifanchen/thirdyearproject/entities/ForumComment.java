package com.feifanchen.thirdyearproject.entities;

import com.feifanchen.thirdyearproject.dao.UserService;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Forumcomment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ForumComment implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usr_id", nullable=false)
    private User c_usr;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id", nullable=false)
    private ForumPost post;

    private String content;

    private Timestamp create_time;

    private int report;

    public long getId(){return id;}

    public void setId(long id) {
        this.id = id;
    }

    public User getUsr_id(){return c_usr;}

    public void setUsr_id(User usr) {
        this.c_usr = usr;
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
