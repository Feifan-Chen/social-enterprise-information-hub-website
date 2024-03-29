package com.feifanchen.thirdyearproject.entities;

import javax.validation.constraints.Size;

public class YoutubeSearchCriteria {

    @Size(min=5, max=200, message="Search term must be between 5 and 64 characters")
    private String queryTerm;

    public YoutubeSearchCriteria(){}

    public String getQueryTerm() {
        return queryTerm;
    }

    public void setQueryTerm(String queryTerm) {
        this.queryTerm = queryTerm;
    }

}