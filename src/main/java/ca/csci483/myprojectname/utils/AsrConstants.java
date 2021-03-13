/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.csci483.myprojectname.utils;

/**
 *
 * @author travis
 */
public class AsrConstants {

    // Might seem unnecessary to have MAX/MINS as separate fields,
    // but if this was to be changed later (e.g. 5 positives and 3 major points), 
    // then this makes that modification much easier.
    public static final int MAX_POSITIVES = 5;
    public static final int MAX_NEGATIVES = 5;
    public static final int MAX_MAJOR_POINTS = 5;
    public static final int MAX_MINOR_POINTS = 5;
    
    public static final int MIN_POSITIVES = 1;
    public static final int MIN_NEGATIVES = 1;
    public static final int MIN_MAJOR_POINTS = 1;
    public static final int MIN_MINOR_POINTS = 1;

}
