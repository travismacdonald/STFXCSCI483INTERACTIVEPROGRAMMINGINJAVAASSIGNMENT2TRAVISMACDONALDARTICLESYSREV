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
import org.primefaces.event.RateEvent;

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
        System.out.println("setCurRev called " + curReview.getId());
        this.curReview = curReview;
        this.curScore = reviewScores.getOrDefault(curReview.getId(), 0);
    }

    public int getCurScore() {
        String printStr = "getCurScore called";
        System.out.println(printStr);
        return curScore;
    }

    public void setCurScore(int curScore) {
        System.out.println("set cur score called");
        this.curScore = curScore;
        reviewScores.put(curReview.getId(), curScore);
    }

    public void onRate(RateEvent<Integer> rateEvent) {
        setCurScore(rateEvent.getRating());
    }

    public void onCancel(RateEvent<Integer> rateEvent) {
        setCurScore(0);
    }

}
