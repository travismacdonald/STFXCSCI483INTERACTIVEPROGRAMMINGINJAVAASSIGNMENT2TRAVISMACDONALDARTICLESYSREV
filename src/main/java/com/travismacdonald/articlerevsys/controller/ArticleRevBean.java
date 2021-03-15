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

    private Review curReview = null;
    private int curScore;

    public Review getCurReview() {
        return curReview;
    }

    public void setCurReview(Review curReview) {
        this.curReview = curReview;
        this.curScore = reviewScores.getOrDefault(curReview.getId(), 0);
    }

    public int getCurScore() {
        return curScore;
    }

    public void setCurScore(int curScore) {
        this.curScore = curScore;
        reviewScores.put(curReview.getId(), curScore);
    }
    
    
    
}
