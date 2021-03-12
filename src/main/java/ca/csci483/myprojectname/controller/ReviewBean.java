/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.csci483.myprojectname.controller;

import ca.csci483.myprojectname.model.Publication;
import ca.csci483.myprojectname.model.Review;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@SessionScoped
@Named("reviewBean")
public class ReviewBean implements Serializable {
    
    private Review review;    

    public void attemptReviewSubmission() {
        System.out.println("aoseunthaosnetuhaosenuth");
    }
    
}
