/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiassignment2;

import java.util.ArrayList;

/**
 *
 * @author Ann
 */
public class TT extends SearchMethod{
    int ValidModels;
    public TT(){
        code="TT";
    }

    @Override
    public String methodOutput(boolean sa) {
        String result = Integer.toString(ValidModels);
        if(sa==true){
            
            return "YES:" + result;
        }
        else {
            return "No";
        }
    }

    @Override
    public boolean methodEntails(String query, KBase kb) {
        ValidModels = 0;
		boolean[] models = new boolean[kb.getSymbols().size()]; //number of symbols
		int temp = models.length;
		int ModSize = (temp)^2; //total number of permutations
		for (int i =0; i< ModSize; i++){ // for each permutation of symbols
			// set true/false
			for (int j =0; j< kb.getSymbols().size(); j++){ //for each symbol in the permutation
				models[j] = ((i&(1<<j)) !=0);
			}
			kb.clauses = models; //setting KB state
			
			boolean allTrue = true;
			// check kb is true
			for (int j =0; j< kb.getClauses().size(); j++){//for each sentence
				if(!models[j]) {
					allTrue = false;
				}
			}
			//check if ask is true
			boolean ask = false;
//			if (askIsTrue()) {
//				ask = true;
//			}
			
			if (allTrue && ask){
				ValidModels ++;
			}
		}
		
        return ValidModels > 0;
    }

    @Override
    public boolean premiseContains(String clause, String ask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
