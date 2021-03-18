package com.feifanchen.thirdyearproject.entities;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import javax.persistence.Entity;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Topic")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Topic implements java.io.Serializable{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String content;

        private long add_by;

        private Timestamp add_at;

        @ManyToMany
        @JoinTable(name = "Youtubetopic", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "youtube_id"))
        private List<YouTubeVideo> youtube;

        @ManyToMany
        @JoinTable(name = "Podcasttopic", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "podcast_id"))
        private List<Podcast> podcast;

        @ManyToMany
        @JoinTable(name = "Eventtopic", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
        private List<Event> event;

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

        public List<YouTubeVideo> getYoutube() {
        return youtube;
    }

        public void setYoutube(List<YouTubeVideo> youtube) {
        this.youtube = youtube;
    }

        public List<Podcast> getPodcasts() {
        return podcast;
    }

        public void setPodcasts(List<Podcast> podcasts) {
        this.podcast = podcasts;
    }

        public List<Event> getEvent() {
        return event;
    }

        public void setEvent(List<Event> event) {
        this.event = event;
    }
}
