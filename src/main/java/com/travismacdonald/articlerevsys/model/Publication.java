package com.travismacdonald.articlerevsys.model;

import java.io.Serializable;

public class Publication implements Serializable {

    private final String url;
    private final String title;
    private int id;

    public Publication(String title, String url, int id) {
        this.title = title;
        this.url = url;
        this.id = -1;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
