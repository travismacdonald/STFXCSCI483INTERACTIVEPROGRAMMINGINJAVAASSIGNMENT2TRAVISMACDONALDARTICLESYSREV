/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.csci483.myprojectname.controller;

import ca.csci483.myprojectname.model.Publication;
import ca.csci483.myprojectname.model.Review;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@SessionScoped
@Named("reviewBean")
public class ReviewBean implements Serializable {

    private Review review = new Review();   

    public void attemptReviewSubmission() {
        System.out.println("Reviewer name: " + review.getReviewerName());
        for (String point : review.getMajorPoints()) {
            System.out.println("point: " + point);
        }
    }

    public String getReviewerName() {
        return review.getReviewerName();
    }

    public void setReviewerName(String reviewerName) {
        review.setReviewerName(reviewerName);
    }
    
    public void addMajorPoint() {
        review.addMajorPoint();
        System.out.println("fuck you");
        System.out.println(review.getMajorPoints().size());
    }
    
    public List<String> getMajorPoints() {
        return review.getMajorPoints();
    }
    
    public void setMajorPoints(List<String> majorPoints) {
        review.setMajorPoints(majorPoints);
    }

}
