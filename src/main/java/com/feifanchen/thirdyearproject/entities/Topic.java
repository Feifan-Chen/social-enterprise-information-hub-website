package com.feifanchen.thirdyearproject.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

import javax.persistence.Entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.StringTokenizer;

@Entity
@Table(name = "Topic")
public class Topic {

        @Id
        @GeneratedValue
        private long id;

        private String content;

        private long add_by;

        private Timestamp add_at;

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

}
