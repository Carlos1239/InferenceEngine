/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Carl
 */
public class FC extends SearchMethod {
   
    public FC(){
    code= "FC";
    entails = new ArrayList<>();
    }
    
    
    @Override
    public String methodOutput(boolean entail) {
       String result = "";
       if(entail==true){
           result = "YES:";
           for(int i=0; i<entails.size();i++){
               result += entails.get(i)+",";
           }
           result += a;
       }
       else{
           result ="NO";
       }
       return result;
    }
    
    

    @Override
    public boolean methodEntails(ArrayList<String> agenda, ArrayList<String> clauses, ArrayList<Integer> count, String query) {
        a=query;
    while(!agenda.isEmpty()){
        String ask = agenda.remove(0);
        entails.add(ask);
        for(int i=0;i<clauses.size();i++){
            if(premiseContains(clauses.get(i),ask)){
                Integer x = count.get(i);
                count.set(i,--x);
                if(count.get(i)==0){
                    String head = clauses.get(i).split("=>")[1];
                    if(head.equals(query))
                        return true;
                    agenda.add(head);
                }
            }
        }
    }
        return false;
    }

    @Override
    public boolean premiseContains(String clause, String ask) {
        String premise = clause.split("=>")[0];
        String[] conjuncts = premise.split("&");
        if(conjuncts.length==1){
            return premise.equals(ask);
        }
        else{
            return Arrays.asList(conjuncts).contains(ask);     
        }
    }
    
    
    
    
    
    
}