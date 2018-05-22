/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

import java.util.ArrayList;

/**
 *
 * @author Carl
 */
public abstract class SearchMethod {
   
    public String code;
    public ArrayList<String> entails;
    public String a;
    public abstract String methodOutput(boolean result);
    public abstract boolean methodEntails(String query, KBase kb);
    public abstract boolean premiseContains(String clause, String ask);
    
}