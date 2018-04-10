package com.esi.arabizi.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

public class DataLoader {
	
	private final String folderPath = "serialized_Data/";
	
	private String dataFileName = "Corpus_transliteration";
	
	private final String prefix = "Serialized_";

    
    private Map<String, Integer> wordFrequencyMap = null;
    
    public DataLoader(String dataFileName) {
    	
    	this.dataFileName = dataFileName;
        
    }
    
    public DataLoader() {
    	
    }
    
    

	public Map<String, Integer> chargementDuModeleDelangue() {
		
	try {
			setWordFrequencyMap(this.deserializeObject(folderPath+prefix+dataFileName+".ser"));
			
			
			
		} catch (ClassNotFoundException e) {
		
			System.err.println("Probleme lors du chargement du modele de langue");
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			System.err.println("Probleme lors de l'ouverture du fichier"+prefix+dataFileName+".ser");
			
			e.printStackTrace();
		}
	
	  return wordFrequencyMap;
	}


	
	@SuppressWarnings("unchecked")
	public HashMap<String, Integer> deserializeObject(String fileName) throws IOException, ClassNotFoundException {
		   
		   HashMap<String, Integer> map=null;
		      
		         FileInputStream fis = new FileInputStream(fileName);
		         
		         ObjectInputStream ois = new ObjectInputStream(fis);
		         
		         map = (HashMap<String, Integer>)(ois.readObject());
		         
		         ois.close();
		         
		         fis.close();
		      
		      return map;
	   }
   

	public Map<String, Integer> getWordFrequencyMap() {
		return wordFrequencyMap;
	}



	public void setWordFrequencyMap(HashMap<String, Integer> wordFrequencyMap) {
		this.wordFrequencyMap = wordFrequencyMap;
	}
	
}
