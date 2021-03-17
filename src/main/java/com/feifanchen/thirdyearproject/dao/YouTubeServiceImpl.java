package com.feifanchen.thirdyearproject.dao;

import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class YouTubeServiceImpl implements YouTubeService {

    private static final long MAX_SEARCH_RESULTS = 5;

    private final static Logger log = LoggerFactory.getLogger(TopicServiceImpl.class);

    private YouTubeRepository youTubeRepository;
    @Autowired
    public YouTubeServiceImpl(YouTubeRepository youTubeRepository) {
        this.youTubeRepository = youTubeRepository;
    }

    @Override
    public long count() {
        return youTubeRepository.count();
    }

    @Override
    public Iterable<YouTubeVideo> findAll() {
        return youTubeRepository.findAll();
    }

    @Override
    public Iterable<YouTubeVideo> findAllByTopics() {
        return youTubeRepository.findAll();
    }

    @Override
    public Iterable<YouTubeVideo> findAllLatest() {
        return youTubeRepository.findAll(Sort.by(Sort.Direction.DESC,"time"));
    }

    @Override
    public YouTubeVideo findOne(long id) {
        return youTubeRepository.findById(id).orElse(null);
    }

    @Override
    public YouTubeVideo save(YouTubeVideo youTube) {
        return youTubeRepository.save(youTube);
    }

    @Override
    public void deleteById(long id) {
        youTubeRepository.deleteById(id);
    }

    /**
     * Returns the first 5 YouTube videos that match the query term
     */
    @Override
    public List<YouTubeVideo> fetchVideosByQuery(String queryTerm) {
        List<YouTubeVideo> videos = new ArrayList<YouTubeVideo>();

        try {
            //instantiate youtube object
            YouTube youtube = getYouTube();

            //define what info we want to get
            YouTube.Search.List search = youtube.search().list("id,snippet");

            //set our credentials
            String apiKey = "AIzaSyDdpJKzCPUP7ZiQCsKDbJ5r7OjOxAZbcTk";
            search.setKey(apiKey);

            //set the search term
            search.setQ(queryTerm);

            //we only want video results
            search.setType("video");

            //set the fields that we're going to use
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/description,snippet/publishedAt,snippet/thumbnails/default/url)");

            //set the max results
            search.setMaxResults(MAX_SEARCH_RESULTS);

            DateFormat df = new SimpleDateFormat("MMM dd, yyyy");

            //perform the search and parse the results
            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                for (SearchResult result : searchResultList) {
                    YouTubeVideo video = new YouTubeVideo();
                    video.setTitle(result.getSnippet().getTitle());
                    video.setUrl(buildVideoUrl(result.getId().getVideoId()));
                    video.setThumbnail(result.getSnippet().getThumbnails().getDefault().getUrl());
                    video.setDescription(result.getSnippet().getDescription());

                    //parse the date
                    DateTime dateTime = result.getSnippet().getPublishedAt();
                    Date date = new Date(dateTime.getValue());
                    video.setTime(date);

                    videos.add(video);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("return successful");
        return videos;
    }


    /**
     * Constructs the URL to play the YouTube video
     */
    @Override
    public String buildVideoUrl(String videoId) {
        StringBuilder builder = new StringBuilder();
        builder.append("https://www.youtube.com/watch?v=");
        builder.append(videoId);

        return builder.toString();
    }


    /**
     * Instantiates the YouTube object
     */
    @Override
    public YouTube getYouTube() {
        YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(),
                (request) -> {}).setApplicationName("youtube-spring-boot-demo").build();

        return youtube;
    }

}
