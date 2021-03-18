package com.feifanchen.thirdyearproject.entities;

public class TopicModel {

    private Topic topic;

    private boolean ischecked;

    public TopicModel(Topic topic, boolean ischecked){
        this.topic = topic;
        this.ischecked = ischecked;
    }

    public boolean isIschecked() {
        return ischecked;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
