package com.esi.arabizi.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.esi.arabizi.tools.WordFrequencyCounter;

public class LanguageModel {
	
	String outputFile;
	
	HashMap<String, Integer> wordFrequencyMap=null;
	
	WordFrequencyCounter frequencyCounter ;
	
	public LanguageModel(String outputFile) {
		
		this.outputFile = outputFile;
	}
	
	public LanguageModel() {
		
		
	}
  
   public void serializeObject(Object object) throws IOException {

       FileOutputStream fos = new FileOutputStream(outputFile+".ser");
              
       ObjectOutputStream oos = new ObjectOutputStream(fos);
              
       oos.writeObject(object);
              
       oos.close();
              
       fos.close();
              
       System.out.println("Serialized HashMap data is saved in "+outputFile+".ser\n");

   }
   
	public void createLm(String corpusName) throws IOException {
		
	    frequencyCounter = new WordFrequencyCounter();
		
	    wordFrequencyMap = (HashMap<String, Integer>)frequencyCounter.countMostFrequentWords(corpusName);

		System.out.println("Language model creation finish\n");
		
		serializeObject(wordFrequencyMap);
    }
	
   public static void main(String [] args) throws ClassNotFoundException
   {
	   String corpusName = "Corpus_transliteration";
	   
	   String prefix = "Serialized_";
	   
	   LanguageModel languageModel = new LanguageModel(prefix+corpusName);
	   
	   try {
		   long startTime = System.currentTimeMillis();
		   
		   languageModel.createLm(corpusName);
		   
		   long elapsedTime = System.currentTimeMillis() - startTime;
		      
		    System.out.println("Elapse Time Creattion: "+elapsedTime/1000+" s");
		    
		      
	   } catch (IOException e) {
		
		   e.printStackTrace();
	   }
	   
  
   }


	public HashMap<String, Integer> getWordFrequencyMap() {
		
		return wordFrequencyMap;
	}


	public void setWordFrequencyMap(HashMap<String, Integer> wordFrequencyMap) {
		
		this.wordFrequencyMap = wordFrequencyMap;
	}
}