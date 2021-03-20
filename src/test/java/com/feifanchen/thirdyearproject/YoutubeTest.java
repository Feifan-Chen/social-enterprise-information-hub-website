package com.feifanchen.thirdyearproject;

import com.feifanchen.thirdyearproject.dao.YouTubeRepository;
import com.feifanchen.thirdyearproject.entities.Topic;
import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class YoutubeTest {
    @Autowired
    private YouTubeRepository repo;
    @Autowired
    private TestEntityManager testEntityManager;


    @Test
    public void newItemTest() {
        Topic topic = new Topic();
        topic.setContent("test");
        testEntityManager.persist(topic);
        Topic online = testEntityManager.find(Topic.class,(long)2);
        YouTubeVideo youTubeVideo = new YouTubeVideo();
        youTubeVideo.setTitle("test");
        youTubeVideo.setUrl("Lo0Ks-0UUeY");
        youTubeVideo.setAdd_by("Feifan");
        youTubeVideo.setAdd_at(new Timestamp(System.currentTimeMillis()));
        youTubeVideo.addTopic(topic);
        youTubeVideo.addTopic(online);
        repo.save(youTubeVideo);
    }

    @Test
    public void addTopicToExist(){
        YouTubeVideo youTubeVideo = testEntityManager.find(YouTubeVideo.class,(long)7);
        Topic online = testEntityManager.find(Topic.class,(long)2);
        youTubeVideo.addTopic(online);
        repo.save(youTubeVideo);
    }

    @Test
    public void removeTopics(){
        YouTubeVideo youTubeVideo = testEntityManager.find(YouTubeVideo.class,(long)7);
        Topic online = testEntityManager.find(Topic.class,(long)5);
        youTubeVideo.removeTopic(online);
    }

    @Test
    public void removeVideo(){
        YouTubeVideo youTubeVideo = testEntityManager.find(YouTubeVideo.class,(long)9);
        repo.delete(youTubeVideo);
    }
}
