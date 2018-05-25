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
    ArrayList<String> symbols;
    String query;
    String[] sentences;
    public KBase(){
        symbols = new ArrayList();
        agenda = new ArrayList();
        clause = new ArrayList();
        count = new ArrayList();
        fact = new ArrayList();
    }  
    
    public void KBaseSenteces(String[] s){

        for (String sentence : s) {
            if (!sentence.contains("=>")) {
                agenda.add(sentence);
                fact.add(sentence);
            } else {
                clause.add(sentence);
                count.add(sentence.split("&").length);
            }
            
            
            
        }
    }
    
    public void setSymbols(String[] s){
        for (String sentence : s) {
            String[] tempString = sentence.split("&");
            for (String a:tempString){
                String[] last = a.split("=>");
                for(String t:last){
                    if(!symbols.contains(t)){
                symbols.add(t);
                
                    }
                }
            }
            
        }
            
            
            
        
    }
    
    public ArrayList<String> getAgenda(){
        return agenda;
    }
    public ArrayList<String> getFact(){
        return fact;
    }
    public ArrayList<String> getClauses(){
        return clause;
    }
    public ArrayList<Integer> getCount(){
        return count;
    }
    public ArrayList<String> getSymbols(){
        return symbols;
    }
}
