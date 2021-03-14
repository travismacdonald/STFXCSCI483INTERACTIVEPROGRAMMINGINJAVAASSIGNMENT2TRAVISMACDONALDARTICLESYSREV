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

    private static final String SUBMISSION_ERROR = "Submission Error";

    private static final String REVIEW_MSG = "rev-msg";

    private Review review = new Review();

    private String publicationTitle = "";
    private String publicationUrl = "";
    private String publicationSummary = "";

    private List<String> positives = new ArrayList(Arrays.asList(""));
    private List<String> negatives = new ArrayList(Arrays.asList(""));
    private List<String> majorPoints = new ArrayList(Arrays.asList(""));
    private List<String> minorPoints = new ArrayList(Arrays.asList(""));

    @Enumerated(EnumType.STRING)
    private Recommendation recommendation;
    private String reviewerName = "";

    public void attemptReviewSubmission() {
        if (validateSubmissionForm()) {
            System.out.println("valid!");
        }
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
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

    public String getPublicationSummary() {
        return publicationSummary;
    }

    public void setPublicationSummary(String publicationSummary) {
        this.publicationSummary = publicationSummary;
    }

    public void addMajorPoint() {
        majorPoints.add("");
        System.out.println("addMajorPointCalled");
    }

    public void addMinorPoint() {
        minorPoints.add("");
        System.out.println("addMinorPointCalled");
    }

    public void addPositive() {
        positives.add("");
        System.out.println("addPositiveCalled");
    }

    public void addNegative() {
        negatives.add("");
        System.out.println("addNegative called");
    }

    public void removeMajorPoint() {
        int total = majorPoints.size();
        majorPoints.remove(total - 1);
        System.out.println("REMOVED major");
    }

    public void removeMinorPoint() {
        int total = minorPoints.size();
        minorPoints.remove(total - 1);
        System.out.println("REMOVED minor");
    }

    public void removePositive() {
        int total = positives.size();
        positives.remove(total - 1);
        System.out.println("REMOVED Positive");
    }

    public void removeNegative() {
        int total = negatives.size();
        negatives.remove(total - 1);
        System.out.println("REMOVED negatives");
    }

    public List<Recommendation> getRecommendations() {
        return Arrays.asList(Recommendation.values());
    }

    public Recommendation getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
    }

    public boolean maxMajorPointsReached() {
        return majorPoints.size() >= AsrConstants.MAX_MAJOR_POINTS;
    }

    public boolean maxMinorPointsReached() {
        return minorPoints.size() >= AsrConstants.MAX_MINOR_POINTS;
    }

    public boolean maxPositivesReached() {
        return positives.size() >= AsrConstants.MAX_POSITIVES;
    }

    public boolean maxNegativesReached() {
        return negatives.size() >= AsrConstants.MAX_NEGATIVES;
    }

    public boolean minMajorPointsReached() {
        return majorPoints.size() <= AsrConstants.MIN_MAJOR_POINTS;
    }

    public boolean minMinorPointsReached() {
        return minorPoints.size() <= AsrConstants.MIN_MINOR_POINTS;
    }

    public boolean minPositivesReached() {
        return positives.size() <= AsrConstants.MIN_POSITIVES;
    }

    public boolean minNegativesReached() {
        return negatives.size() <= AsrConstants.MIN_NEGATIVES;
    }

    /**
     * Validates each field in the review submission. 'res' becomes false when
     * at least one validate method fails (i.e. returns false). However, it is
     * not returned right away so that multiple errors can be logged at once,
     * rather than returning after the first validation error, thereby only
     * logging the first error.
     */
    private boolean validateSubmissionForm() {
        boolean res = true;

        if (!validatePublicationTitle()) {
            res = false;
        }
        if (!validatePublicationUrl()) {
            res = false;
        }
        if (!validatePublicationSummary()) {
            res = false;
        }
        if (!validatePositives()) {
            res = false;
        }
        if (!validateNegatives()) {
            res = false;
        }
        if (!validateMajorPoints()) {
            res = false;
        }
        if (!validateMinorPoints()) {
            res = false;
        }
        if (!validateReviewerName()) {
            res = false;
        }
        return res;
    }

    private boolean validatePublicationTitle() {
        if (publicationTitle.isBlank()) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide a publication title."
            );
            return false;
        }
        return true;
    }

    private boolean validatePublicationUrl() {
        if (publicationUrl.isBlank()) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide a publication URL."
            );
            return false;
        }
        return true;
    }

    private boolean validatePublicationSummary() {
        if (publicationSummary.isBlank()) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide a publication summary."
            );
            return false;
        }
        return true;
    }

    private boolean validatePositives() {
        if (countNumberOfNonBlankPoints(positives) == 0) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide at least one positive."
            );
            return false;
        }
        return true;
    }

    private boolean validateNegatives() {
        if (countNumberOfNonBlankPoints(negatives) == 0) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide at least one negative."
            );
            return false;
        }
        return true;
    }

    private boolean validateMajorPoints() {
        if (countNumberOfNonBlankPoints(majorPoints) == 0) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide at least one major point."
            );
            return false;
        }
        return true;
    }

    private boolean validateMinorPoints() {
        if (countNumberOfNonBlankPoints(minorPoints) == 0) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide at least one minor point."
            );
            return false;
        }
        return true;
    }
    
    private boolean validateReviewerName() {
        if (reviewerName.isBlank()) {
            showSubmissionError(
                    SUBMISSION_ERROR,
                    "Please provide a reviewer name."
            );
            return false;
        }
        return true;
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

    private int countNumberOfNonBlankPoints(List<String> points) {
        int nonBlank = 0;
        for (String point : points) {
            if (!point.isBlank()) {
                nonBlank++;
            }
        }
        return nonBlank;
    }

}
