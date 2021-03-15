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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named("homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    private List<Review> reviews = ArsRepository.getInstance().getAllReviews();

    private Review curReview = null;

    public String navToMakeReview() {
        System.out.println("hi");
        return "/make_review.xhtml?faces-redirect=true";
    }

    public void doSomething() {
        System.out.println("hi");
//        System.out.println("i'm doing something!");
//        ArsRepository repo = ArsRepository.getInstance();
//        repo.getAllPublications();
//        System.out.println("i'm doing something");
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
//        if (reviews == null) {
//            reviews = ArsRepository.getInstance().getAllReviews();
//        }
//        System.out.println("made it here");
//        return reviews;

        return reviews;
    }

    public void openReviewDialog(Review review) {
        setCurReview(review);
        System.out.println("openReviewwwwwwwwww dialog called");
        System.out.println("revId: " + review.getId());
//        final Map<String, Object> options = new HashMap();
//        options.put("modal", true);
//        PrimeFaces.current().dialog().openDynamic("level1", options, null);
        System.out.println("my new print statement");
    }
    
    public String getCurReviewPublicationTitle() {
        if (curReview == null) {
            return "Fuck";
        }
        return curReview.getPublication().getTitle();
    }

}
