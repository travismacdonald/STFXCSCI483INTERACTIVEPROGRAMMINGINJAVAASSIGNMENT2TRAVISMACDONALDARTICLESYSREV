/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.travismacdonald.articlerevsys.controller;

import com.travismacdonald.articlerevsys.model.Review;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named("articleRevBean")
@ApplicationScoped
public class ArticleRevBean implements Serializable {
    
    final Map<Integer, Integer> reviewScores = new HashMap();


    public Integer getScore(int reviewId) {
        return reviewScores.get(reviewId);
    }
    
    public void setScore(int reviewId, int score) {
        reviewScores.put(reviewId, score);
    }
    
}
