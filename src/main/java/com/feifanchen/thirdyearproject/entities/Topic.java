package com.feifanchen.thirdyearproject.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import javax.persistence.Entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Topic")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Topic implements java.io.Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String content;

        private String add_by;

        private Timestamp add_at;

        @JsonIgnore
        @ManyToMany(mappedBy = "y_topic", fetch=FetchType.EAGER)
        private Set<YouTubeVideo> youtube;

        @JsonIgnore
        @ManyToMany(mappedBy = "p_topic", fetch=FetchType.EAGER)
        private Set<Podcast> podcast;

        @JsonIgnore
        @ManyToMany(mappedBy = "t_topic", fetch=FetchType.EAGER)
        private Set<Tedtalk> ted;

        @JsonIgnore
        @ManyToMany(mappedBy = "e_topic", fetch=FetchType.EAGER)
        private Set<Event> event;

        @JsonIgnore
        @ManyToMany(mappedBy = "f_topic", fetch=FetchType.EAGER)
        private Set<ForumPost> posts;

    public Topic() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getContent(){
            return this.content;
        }

        public void setContent(String content){
            this.content = content;
        }

        public String  getAdd_by(){
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

        public Set<YouTubeVideo> getYoutube() {
        return youtube;
    }

        public void setYoutube(Set<YouTubeVideo> youtube) {
        this.youtube = youtube;
    }

        public Set<Podcast> getPodcasts() {
        return podcast;
    }

        public void setPodcasts(Set<Podcast> podcasts) {
        this.podcast = podcasts;
    }

        public Set<Tedtalk> getTed() {
        return ted;
    }

        public void setTed(Set<Tedtalk> ted) {
        this.ted = ted;
    }

        public Set<Event> getEvent() {
            return event;
        }

        public void setEvent(Set<Event> event) {
            this.event = event;
        }

        public Set<ForumPost> getPosts() {
            return posts;
        }

        public void setPosts(Set<ForumPost> posts) {
            this.posts = posts;
        }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Topic topic = (Topic) o;
            return id == topic.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}
