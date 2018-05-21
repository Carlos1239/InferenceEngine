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
public class BC extends SearchMethod {
    ArrayList<String> agenda;
    public BC(){
    code ="BC";
    entails = new ArrayList<>();
    agenda = new ArrayList<>();
    }

    @Override
    public String methodOutput(boolean entail) {
       String result;
        
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
    public boolean methodEntails(ArrayList<String> fact, ArrayList<String> clauses, ArrayList<Integer> count, String query) {
        agenda.add(query);
        a=query;
    while(!agenda.isEmpty()){
        String ask = agenda.remove(agenda.size()-1);
        entails.add(ask);
        if(!fact.contains(ask)){
            ArrayList<String> q = new ArrayList<>();
            for(int i=0;i<clauses.size();i++){
            if(premiseContains(clauses.get(i),ask)){
                ArrayList<String> temp = getClauses(clauses.get(i),agenda);
                for (int x=0; x<temp.size();x++){
                    q.add(temp.get(x));
                }
                }
            }
            if(q.isEmpty()){
                return false;
            }
            else
            {
                for(int i =0; i<q.size();i++){
                    if(!entails.contains(q.get(i))){
                        agenda.add(q.get(i));
                    }
                }
            }
        
        }
        
    }
        return true;
       
    }
    
    public static ArrayList<String> getClauses(String clause, ArrayList<String> agenda){
       
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
        String premise = clause.split("=>")[1];
      
        return premise.equals(ask);
    }
    
}
