/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travismacdonald.articlerevsys.controller;

import com.travismacdonald.articlerevsys.model.ArsRepository;
import com.travismacdonald.articlerevsys.model.Review;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    private List<Review> reviews = ArsRepository.getInstance().getAllReviews();

    private Review curReview = null;

    public String navToHome() {
        return "/home.xhtml?faces-redirect=true";
    }
    
    public String navToMakeReview() {
        return "/make_review.xhtml?faces-redirect=true";
    }
    
    public String navToScoreReview() {
        return "/reviews.xhtml?faces-redirect=true";
    }

    public void setCurReview(Review review) {
        curReview = review;
    }

    public Review getCurReview() {
        return curReview;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void openReviewDialog(Review review) {
        setCurReview(review);
    }

    public String getCurReviewPublicationTitle() {
        if (curReview == null) {
            return "";
        }
        return curReview.getPublication().getTitle();
    }

    public String getFormattedPositivesForReview(Review review) {
        if (curReview == null) {
            return "";
        }
        return getFormattedPoints(review.getPositives());
    }

    public String getFormattedNegativesForReview(Review review) {
        if (curReview == null) {
            return "";
        }
        return getFormattedPoints(review.getNegatives());
    }

    public String getFormattedMajorPointsForReview(Review review) {
        if (curReview == null) {
            return "";
        }
        return getFormattedPoints(review.getMajorPoints());
    }

    public String getFormattedMinorPointsForReview(Review review) {
        if (curReview == null) {
            return "";
        }
        return getFormattedPoints(review.getMinorPoints());
    }

    public String getFormattedPoints(List<String> points) {
        String toReturn = "";
        toReturn += "1. ";
        toReturn += points.get(0);
        for (int i = 1; i < points.size(); i++) {
            toReturn += "\n\n";
            toReturn += String.format("%d. ", i + 1);
            toReturn += points.get(i);
        }
        return toReturn;
    }

}
