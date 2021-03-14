package com.travismacdonald.articlerevsys.model;

import java.io.Serializable;

public class Publication implements Serializable {

    private final String url;
    private final String title;

    public Publication(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

}
