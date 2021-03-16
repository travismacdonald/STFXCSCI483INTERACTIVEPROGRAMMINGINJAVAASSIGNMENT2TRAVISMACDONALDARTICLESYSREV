package com.travismacdonald.articlerevsys.model;

import java.io.Serializable;
import java.util.List;

public class Review implements Serializable {

    private Publication publication;
    private String summary;

    private List<String> positives;
    private List<String> negatives;
    private List<String> majorPoints;
    private List<String> minorPoints;
    
    private Recommendation recommendation;
    private String reviewerName;
    
    private int id;

    public Review(
            Publication publication,
            String summary,
            List<String> positives,
            List<String> negatives,
            List<String> majorPoints,
            List<String> minorPoints,
            Recommendation recommendation,
            String reviewerName,
            int id
    ) {
        this.publication = publication;
        this.summary = summary;
        this.positives = positives;
        this.negatives = negatives;
        this.majorPoints = majorPoints;
        this.minorPoints = minorPoints;
        this.recommendation = recommendation;
        this.reviewerName = reviewerName;
        this.id = id;
    }

    public Publication getPublication() {
        return publication;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getMajorPoints() {
        return majorPoints;
    }

    public List<String> getMinorPoints() {
        return minorPoints;
    }

    public List<String> getPositives() {
        return positives;
    }

    public List<String> getNegatives() {
        return negatives;
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
