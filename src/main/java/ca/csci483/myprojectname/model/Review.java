package ca.csci483.myprojectname.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Review implements Serializable {
    
    private Publication publication;
    
    private String summary = "";
    
    private List<String> positives = new ArrayList(Arrays.asList(""));
    
    private List<String> negatives = new ArrayList(Arrays.asList(""));
    
    private List<String> majorPoints = new ArrayList(Arrays.asList(""));
    
    private List<String> minorPoints = new ArrayList(Arrays.asList(""));
    
    private Recommendation recommendation;
    
    private String reviewerName = "";

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
    
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    
}
