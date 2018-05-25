/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author Carl
 */
public class BC extends SearchMethod {
    ArrayList<String> agenda;
    ArrayList<String> fact;

    ArrayList<String> clause;

    KBase KB;
    public BC(){
    code ="BC";
    entails = new ArrayList<>();
    agenda = new ArrayList<>();
    fact = new ArrayList<>();
    clause = new ArrayList<>();
    
    }

    @Override
    public String methodOutput(boolean entail) {
       String result;
       //prints results ot backwards for backwards chaining 
       if(entail==true){
           result = "YES:";
           for(int i=entails.size()-1; i>=0;i--){
               if(i==0)
               result += entails.get(i);
               else{
                   result += entails.get(i)+",";
               }
           }   
       }
       else{
           result ="NO";
       }
       return result;
    }
    
    

    @Override
    public boolean methodEntails(String query, KBase kb) {
        //acquires information from Knowledge base
        fact = kb.getFact();
        clause = kb.getClauses();
        agenda.add(query);
        a=query;
        while(!agenda.isEmpty()){
            String ask = agenda.remove(agenda.size()-1); //Backword chaining occurs similarly to DFS but going from goal to start point
            entails.add(ask);
            if(!fact.contains(ask)){
                ArrayList<String> q = new ArrayList<>();
                clause.stream().filter((c) -> (premiseContains(c,ask))).map((c) -> getClauses(c,agenda)).forEachOrdered((temp) -> {
                    //checks clauses and sees if it contains the ask
                    for (int x=0; x<temp.size();x++){
                        q.add(temp.get(x));
                    }
                });
                if(!q.isEmpty()){
                    q.stream().filter((p) -> (!entails.contains(p))).forEachOrdered((p) -> {
                        agenda.add(p);
                    });
                }
                else
                {
                    return false;
                }

            }
        
    }
        return true;
       
    }
    
    public static ArrayList<String> getClauses(String clause, ArrayList<String> agenda){
       //splits up implications 
        String premise = clause.split("=>")[0];
        ArrayList<String> temp = new ArrayList<>();
        String [] conjuncts = premise.split("&");
        for (String conjunct : conjuncts) {
            if (!agenda.contains(conjunct)) {
                temp.add(conjunct);
            }
        }
        return temp;
    }

    @Override
    public boolean premiseContains(String clause, String ask) {
        //checks if the ask is in the clause
        String premise = clause.split("=>")[1];
        return premise.equals(ask);
    }
    
}
