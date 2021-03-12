package ca.csci483.myprojectname.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Review implements Serializable {
    
    private Publication publication;
    
    private String summary;
    
    private List<String> positives;
    
    private List<String> negatives;
    
    private List<String> majorPoints = new ArrayList(Arrays.asList("aoeu", ",,,,", "uea"));
    
    private List<String> minorPoints;
    
    private Recommendation recommendation;
    
    private String reviewerName;

    public List<String> getMajorPoints() {
        return majorPoints;
    }
    
    public void setMajorPoints(List<String> majorPoints) {
        this.majorPoints = majorPoints;
    } 
    
    public void addMajorPoint() {
        majorPoints.add("");
    }
    
    public String getMajorPointAtIx(int ix) {
        return majorPoints.get(ix);
    }
    
    public void setMajorPointAtIx(int ix, String majorPoint) {
        majorPoints.set(ix, majorPoint);
    }
    
    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    
}
