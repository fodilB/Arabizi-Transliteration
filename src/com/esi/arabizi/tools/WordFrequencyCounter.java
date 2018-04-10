/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esi.arabizi.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import java.util.StringTokenizer;


public class WordFrequencyCounter {
     
    
   
    public Map<String, Integer> countMostFrequentWords(String text) throws IOException {	
        
        HashMap<String, Integer> wordFrequencyMap = new HashMap<String, Integer>();	
        
		BufferedReader lecteurAvecBuffer = null;
	    
		String ligne="";  
	    
		String mot="";
		
		Integer count = new Integer(0);
	    
		try{	
		     lecteurAvecBuffer = new BufferedReader(new FileReader(text));
	    }
	    catch(FileNotFoundException exc){	
	    	 System.out.println("Erreur d'ouverture");
	    }
	    
		
		while ((ligne = lecteurAvecBuffer.readLine()) != null)
	     {
                 
            StringTokenizer str = new StringTokenizer(ligne);
            
			while (str.hasMoreTokens()) {
			  
			            mot = str.nextToken();
                        
                        count = wordFrequencyMap.get(mot);
                        
                        count = (count == null) ? 1 : ++count;
                        
                        wordFrequencyMap.put(mot, count);
                        
			
             }
	     }
		

        return wordFrequencyMap;
    }



}
