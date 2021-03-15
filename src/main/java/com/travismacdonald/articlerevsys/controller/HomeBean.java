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
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("homeBean")
@SessionScoped
public class HomeBean implements Serializable {

    private List<Review> reviews = null;

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

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
//        if (reviews == null) {
//            reviews = ArsRepository.getInstance().getAllReviews();
//        }
//        System.out.println("made it here");
//        return reviews;

        return ArsRepository.getInstance().getAllReviews();
    }

}
