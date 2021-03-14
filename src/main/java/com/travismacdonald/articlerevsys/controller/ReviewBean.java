/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travismacdonald.articlerevsys.controller;

import com.travismacdonald.articlerevsys.model.Recommendation;
import com.travismacdonald.articlerevsys.model.Review;
import com.travismacdonald.articlerevsys.utils.AsrConstants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.view.facelets.FaceletContext;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SessionScoped
@Named("reviewBean")
public class ReviewBean implements Serializable {

    private static final String REVIEW_MSG = "rev-msg";

    private Review review = new Review();

    private String publicationTitle = "";

    private String publicationUrl = "";

    public void attemptReviewSubmission() {
        if (validateSubmissionForm()) {
            System.out.println("valid!");
        }

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

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String title) {
        this.publicationTitle = title;
    }

    public String getPublicationUrl() {
        return publicationUrl;
    }

    public void setPublicationUrl(String publicationUrl) {
        this.publicationUrl = publicationUrl;
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

    public void removeMajorPoint() {
        int total = review.getMajorPoints().size();
        review.getMajorPoints().remove(total - 1);
        System.out.println("REMOVED major");
    }

    public void removeMinorPoint() {
        int total = review.getMinorPoints().size();
        review.getMinorPoints().remove(total - 1);
        System.out.println("REMOVED minor");
    }

    public void removePositive() {
        int total = review.getPositives().size();
        review.getPositives().remove(total - 1);
        System.out.println("REMOVED Positive");
    }

    public void removeNegative() {
        int total = review.getNegatives().size();
        review.getNegatives().remove(total - 1);
        System.out.println("REMOVED negatives");
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
        return review.getMajorPoints().size() >= AsrConstants.MAX_MAJOR_POINTS;
    }

    public boolean maxMinorPointsReached() {
        return review.getMinorPoints().size() >= AsrConstants.MAX_MINOR_POINTS;
    }

    public boolean maxPositivesReached() {
        return review.getPositives().size() >= AsrConstants.MAX_POSITIVES;
    }

    public boolean maxNegativesReached() {
        return review.getNegatives().size() >= AsrConstants.MAX_NEGATIVES;
    }

    public boolean minMajorPointsReached() {
        return review.getMajorPoints().size() <= AsrConstants.MIN_MAJOR_POINTS;
    }

    public boolean minMinorPointsReached() {
        return review.getMinorPoints().size() <= AsrConstants.MIN_MINOR_POINTS;
    }

    public boolean minPositivesReached() {
        return review.getPositives().size() <= AsrConstants.MIN_POSITIVES;
    }

    public boolean minNegativesReached() {
        return review.getNegatives().size() <= AsrConstants.MIN_NEGATIVES;
    }
    
    private boolean validateSubmissionForm() {
        // Return res, instead of returning false in each statment,
        // so multiple errors can be logged at once (e.g. no title and no url).
        boolean res = true;
        if (publicationTitle.isBlank()) {
            showSubmissionError(
                    "Submission Error", 
                    "No publication title provided."
            );
            res = false;
        } 
        if (publicationUrl.isBlank()) {
            showSubmissionError(
                    "Submission Error",
                    "No publication URL provided."
            );
            res = false;
        }
        
        return res;
    }

    private void showSubmissionError(String summary, String details) {
        FacesMessage msg = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                summary,
                details
        );
        FacesContext.getCurrentInstance().addMessage(
                REVIEW_MSG,
                msg
        );
    }

}
