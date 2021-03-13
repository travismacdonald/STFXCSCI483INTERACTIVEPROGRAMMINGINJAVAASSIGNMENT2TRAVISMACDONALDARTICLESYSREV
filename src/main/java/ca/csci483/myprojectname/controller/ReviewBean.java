/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.csci483.myprojectname.controller;

import ca.csci483.myprojectname.model.Publication;
import ca.csci483.myprojectname.model.Recommendation;
import ca.csci483.myprojectname.model.Review;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SessionScoped
@Named("reviewBean")
public class ReviewBean implements Serializable {

    private Review review = new Review();

    public void attemptReviewSubmission() {
        
        System.out.println("attemptReviewSubmission called");
        System.out.println("Reviewer name: " + review.getReviewerName());
        for (String point : review.getMajorPoints()) {
            System.out.println("major: " + point);
        }
        for (String point : review.getMinorPoints()) {
            System.out.println("minor: " + point);
        }
        for (String point : review.getPositives()) {
            System.out.println("positive: " + point);
        }
        for (String point : review.getNegatives()) {
            System.out.println("negative: " + point);
        }
        System.out.println("Rec: " + recommendation);
    }

    public String getReviewerName() {
        return review.getReviewerName();
    }

    public void setReviewerName(String reviewerName) {
        review.setReviewerName(reviewerName);
    }

    public List<String> getMajorPoints() {
        return review.getMajorPoints();
    }

    public List<String> getMinorPoints() {
        return review.getMinorPoints();
    }

    public List<String> getPositives() {
        return review.getPositives();
    }

    public List<String> getNegatives() {
        return review.getNegatives();
    }

    public void addMajorPoint() {
        review.getMajorPoints().add("");
        System.out.println("addMajorPointCalled");
    }

    public void addMinorPoint() {
        review.getMinorPoints().add("");
        System.out.println("addMinorPointCalled");
    }

    public void addPositive() {
        review.getPositives().add("");
        System.out.println("addPositiveCalled");
    }

    public void addNegative() {
        review.getNegatives().add("");
        System.out.println("addNegative called");
    }

    public void removeMajorPointAtIx(int ix) {
        for (String point : review.getMajorPoints()) {
            System.out.println("point: " + point);
        }
        review.getMajorPoints().remove(ix);
        System.out.println("REMOVE: " + ix);
    }

    public void removeMinorPointAtIx(int ix) {
        review.getMinorPoints().remove(ix);
        System.out.println("REMOVED MINOR POINT at ix " + ix);
    }

    public void removePositiveAtIx(int ix) {
        review.getPositives().remove(ix);
        System.out.println("REMOVED Positive at ix " + ix);
    }

    public void removeNegativeAtIx(int ix) {
        review.getNegatives().remove(ix);
        System.out.println("REMOVED NEGATIVE at ix " + ix);
    }
    
    public List<Recommendation> getRecommendations() {
        return Arrays.asList(Recommendation.values());
    }
    
    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }
    
    public boolean maxMajorPointsReached() {
        return review.getMajorPoints().size() == 5;
    }
    
    public boolean maxMinorPointsReached() {
        return review.getMinorPoints().size() == 5;
    }
    
    public boolean maxPositivesReached() {
        return review.getPositives().size() == 5;
    }
    
    public boolean maxNegativesReached() {
        return review.getNegatives().size() == 5;
    }
    
    
}
