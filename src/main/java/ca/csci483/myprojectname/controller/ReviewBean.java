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
        System.out.println("addMajorPointCalled");
    }
    
    public void removeMajorPointAtIx(int ix) {
        review.getMajorPoints().remove(ix);
        System.out.println("REMOVE: " + ix);
    }
    
    public List<String> getMajorPoints() {
        return review.getMajorPoints();
    }
    
    public List<String> getMinorPoints() {
        return review.getMinorPoints();
    }
    
    public void addMinorPoint() {
        review.getMinorPoints().add("");
        System.out.println("addMinorPointCalled");
    }
    
    public void removeMinorPointAtIx(int ix) {
        for (String point : review.getMajorPoints()) {
        
        }
        review.getMinorPoints().remove(ix);
        System.out.println("REMOVED MINOR POINT at ix " + ix);
    }

}
