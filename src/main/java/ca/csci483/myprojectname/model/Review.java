package ca.csci483.myprojectname.model;

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

}
