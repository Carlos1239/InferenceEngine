/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

import java.util.*;

/**
 *
 * @author Carl
 */
public class KBase{
    ArrayList<String> agenda;
    ArrayList<String> clause;
    ArrayList<Integer> count;
    ArrayList<String> fact;
    String query;
    String[] sentences;
    public KBase(String[] s){
        sentences = s;
        
    }  
    
    public void KBaseSenteces(){
        
        for (String sentence : sentences) {
            if (!sentence.contains("=>")) {
                agenda.add(sentence);
                fact.add(sentence);
            } else {
                clause.add(sentence);
                count.add(sentence.split("&").length);
            }
        }
    }
}
