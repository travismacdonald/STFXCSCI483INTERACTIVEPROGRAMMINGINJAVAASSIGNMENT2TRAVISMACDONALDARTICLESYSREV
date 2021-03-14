/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travismacdonald.articlerevsys.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("homeBean")
@SessionScoped
public class HomeBean implements Serializable {
    
    public String navToMakeReview() {
        return "/make_review.xhtml?faces-redirect=true";
    }
    
}
