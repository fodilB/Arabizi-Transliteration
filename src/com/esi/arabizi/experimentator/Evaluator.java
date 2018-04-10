package com.esi.arabizi.experimentator;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.esi.arabizi.tools.FileManipulator;

public class Evaluator {
	
	private FileManipulator fileManipulator;
	
	private BufferedReader transliteratedFileReader = null;
	
	private BufferedReader referenceFileReader 		= null;
	
	public Evaluator() {
		
		this.fileManipulator = new FileManipulator();
	}
	
	public  float Accuracy(String transliteratedFile,String referenceFile) throws IOException {
		
		float accuracy;

		String ligne1,ligne2;
		
		float wordHit     = 0;
		
		float wordCounter = 0;
		
		String ch1 , ch2;
		
		StringTokenizer s1,s2;

		transliteratedFileReader = this.fileManipulator.fileReader(transliteratedFile);
		
		referenceFileReader 	 = this.fileManipulator.fileReader(referenceFile);

		while ((ligne1 = transliteratedFileReader.readLine()) != null) {
	
	    	s1 = new StringTokenizer(ligne1);
	    	  
	    	ligne2 = referenceFileReader.readLine();
	    	  
	    	s2 = new StringTokenizer(ligne2);

	    	while (s1.hasMoreTokens()) {
	    		
	    		wordCounter++;
			
				ch1 = s1.nextToken();
				
				ch2 = s2.nextToken();
				
				//System.out.println(ch1+" : "+ch2);
				
				if (ch1.compareTo(ch2) == 0) {
					wordHit++;
					//System.out.println(wordHit);
				}
				
	    	}

		}

		accuracy = (wordHit*100)/wordCounter;
		
		return accuracy;
	}

}
