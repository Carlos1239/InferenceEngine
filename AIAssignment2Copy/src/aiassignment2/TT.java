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
    public TT(){
        code="TT";
    }

    @Override
    public String methodOutput(boolean sa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean methodEntails(String query, KBase kb) {
        int ValidModels = 0;
		boolean[] models = new boolean[kb.count.length] //number of symbols
		int temp = models.length;
		int ModSize = (temp)^2; //total number of permutations
		for (int i =0; i< ModSize; i++){ // for each permutation of symbols
			// set true/false
			for (int j =0; j< kb.symbols.length; j++){ //for each symbol in the permutation
				models[j] = ((i&(1<<j)) !=0); //set the true and false values by shifting 1/0 to the correct positons
			}
			boolean allTrue = true;
			// check kb is true
			for (int j =0; j< kb.agenda.length; j++){ //for each sentence
				if(!models[j]) { //
					allTrue = false;
				}
			}
			//check if ask is true
			boolean ask = false;
			if (askIsTrue()) {
				ask = true;
			}
			
			if (allTrue && ask){
				ValidModels ++;
			}
		}
		
		if (ValidModels > 0){
			System.out.println('YES:' + ValidModels);
		}
		else{
			System.out.println('NO');
		}
    }

    @Override
    public boolean premiseContains(String clause, String ask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
