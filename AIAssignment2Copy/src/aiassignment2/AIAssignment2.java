package aiassignment2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AIAssignment2 {
    public static AIAssignment2 iengine;
    public static String query;
    public static final int METHOD_COUNT = 3;
    public static SearchMethod[] aMethods;

    public static void main(String[] args) {
        
        InitMethods();
        
        if(args.length <2){
            System.out.println("Usage:iengine <method> <filename>");
            System.exit(1);
        }
        try{
            readProblemFile(args[1]);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        String method = args[0];
	SearchMethod thisMethod = null;
		
        //determine which method the user wants to use to solve the puzzles
            for(int i = 0; i < METHOD_COUNT; i++)
            {
                    //do they want this one?
                    if(aMethods[i].code.compareTo(method) == 0)
                    {
                            //yes, use this method.
                            thisMethod = aMethods[i];
                    }
            }

            //Has the method been implemented?
            if(thisMethod == null)
            {
                    //No, give an error
                    System.out.println("Search method identified by " + method + " not implemented. Methods are case sensitive.");
                    System.exit(1);
            }
        String result = thisMethod.methodOutput(thisMethod.methodEntails(query));
        System.out.print(result);       
    }
    
    private static void InitMethods(){
        aMethods = new SearchMethod[METHOD_COUNT];
        aMethods[0]= new FC();
        aMethods[1]=new BC();
        aMethods[2]=new TT();
    }
    
    private static void readProblemFile(String fileName) // this allow only one puzzle to be specified in a problem file 
	{
		String[] sentencesArray;
		try
		{
			//create file reading objects
			FileReader reader = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(reader);
			
                        String tell;
                        String line = buff.readLine().toUpperCase();
                        if(line.equalsIgnoreCase("TELL")){
                            line = buff.readLine();
                            tell=line.replaceAll("\\s","");
                            sentencesArray = tell.split(";");
                            KBase KB = new KBase(sentencesArray);
                            
                            
                        }
                        String ask;
                        line = buff.readLine().toUpperCase();
                        if(line.equalsIgnoreCase("ASK")){
                            line = buff.readLine();
                            ask = line.replaceAll("\\s","");
                            query = ask;
                        }
			
		}
		catch(FileNotFoundException ex)
		{
			//The file didn't exist, show an error
			System.out.println("Error: File \"" + fileName + "\" not found.");
			System.out.println("Please check the path to the file.");
			System.exit(1);
		}
		catch(IOException ex)
		{
			//There was an IO error, show and error message
			System.out.println("Error in reading \"" + fileName + "\". Try closing it and programs that may be accessing it.");
			System.out.println("If you're accessing this file over a network, try making a local copy.");
			System.exit(1);
		}
		
		//this code should be unreachable. This statement is simply to satisfy Eclipse.
		
	}
}