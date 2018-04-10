package com.esi.arabizi.transliterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.esi.arabizi.tools.Combiner;
import com.esi.arabizi.tools.FileManipulator;

public class Transliterator {
	
	private FileManipulator fileManipulator;
	
	private final String parameter = "XX";
			
    private String corpusArabizi="dst.txt";

    @SuppressWarnings("unused")
	private String translatedCorpus="translatedCorpus";
    
    private BufferedReader reader = null;
    
    private PrintWriter writer =  null; 
    
    private WordGenerator wordGenerator = null;
      
    private Map<String, Integer> wordFrequencyMap = null;
    
    private BestCandidate bestCandidateSelector=null;
    
    

	public Transliterator (String srcFile,String dstFile,Map<String, Integer> wordFrequencyMap,BestCandidate bestCandidateSelector) {
		
		this.fileManipulator = new FileManipulator();
		
		this.reader = this.fileManipulator.fileReader(srcFile);
		
		this.writer = this.fileManipulator.fileWriter(dstFile);
		
		this.corpusArabizi = srcFile;
		
		this.translatedCorpus = dstFile;
		
		this.wordGenerator = new WordGenerator();
		
		this.wordFrequencyMap = wordFrequencyMap;
		
		this.bestCandidateSelector = bestCandidateSelector;
		
		
	}




	public void transliterer() {
			
	  String phrase="";  
			
	  String word = "";
	  
	  StringTokenizer str ;
		  
	  List<String> listCondidates;
		
	  Map<String,Candidate>listOfCandidatesWithDistance; 
	
		try {
			
			while ((phrase = this.reader.readLine()) != null)
			 {
			        
			    str = new StringTokenizer(phrase);
			                
				while (str.hasMoreTokens()){
					  
					word = str.nextToken().toString();
			
					Integer[] mad7oroofPos = this.getMad7oroofPos(word);
			
					listCondidates = this.generateCandidats(word,mad7oroofPos);

					//System.out.println("List of Candidates"+listCondidates.toString()+" \nsa taille:"+listCondidates.size());

					String selectedWord = word;
					
					listOfCandidatesWithDistance = generateCondidateWithDistance(word,listCondidates);
				
					Candidate bigParametere = listOfCandidatesWithDistance.get(parameter);
					
					listOfCandidatesWithDistance.remove(parameter);
					
					selectedWord = this.bestCondidate(listOfCandidatesWithDistance,bigParametere.getOccurence(),bigParametere.getDistance());

					if(selectedWord.equals("")) selectedWord = word;
					//Candidate bestCondidate = listOfCandidatesWithDistance.get(selectedWord);
					
					writer.print(selectedWord+" ");

			//		System.out.println(word+" : "+selectedWord);
			
				}
				
				writer.println("");

			 }
			
		} catch (IOException e) {
			
			System.err.println("Probleme lors de l'ouverture du fichier : "+corpusArabizi);
			
			e.printStackTrace();
		}
		
		writer.close();
		
	}

	    
	    private Integer[] getMad7oroofPos(String word) {
	    	
	    	return (Integer[]) wordGenerator.ambiguous7oroofPos(word).toArray(new Integer[0]);
	    }
	    
	    
	    
	    private List<String> generateCandidats(String word,Integer[] mad7oroofPos) {
	    	
	    	List<String> listCondidates = new ArrayList<String>();
	    	
	    	listCondidates.add(word);
	    	
			for(int i=1;i<mad7oroofPos.length+1;i++) {
				
				Combiner<Integer> combiner = new Combiner<Integer>( i, mad7oroofPos );
	
				Integer[] result = new Integer[i];

				while ( combiner.searchNext( result ) ) { 
					
					if(wordGenerator.selectMethod(word,result)){

						  listCondidates.addAll(wordGenerator.remplacer2(word,result));
							
					}else listCondidates.add(wordGenerator.remplacer(word,result));

				}
	          
			}
			
			return listCondidates;
	    	
	    }
	    
	    
	    
	    private Map<String,Candidate> generateCondidateWithDistance(String word,List<String> listCondidates) {
	    	
	    	int bigDistance = 0;
	    	
	    	int bigOcc =0;
	    	
			Iterator<String> CondidateIter =  listCondidates.iterator();
			
			Map<String,Candidate>listOfCandidatesWithDistance = new HashMap<String,Candidate>(); 

			String selectedWord = word;
			
			listOfCandidatesWithDistance.put(selectedWord, new Candidate(0,0));
			
			while(CondidateIter.hasNext()) {

				String candidateWord = CondidateIter.next();
				
				int newOcc = wordFrequencyMap.get(candidateWord)==null?0:wordFrequencyMap.get(candidateWord);
	    
	            if(newOcc > bigOcc)  {
	            	
	            	bigOcc = newOcc;
	            	
	            	selectedWord = candidateWord;
	            	
	            }
	            
	           if(newOcc!=0) {
	        	   
	        	   int distance =this.bestCandidateSelector.computeDistance(word, candidateWord);
	        	   
	        	   Candidate candidate = new Candidate(distance, newOcc);
	            
	        	   listOfCandidatesWithDistance.put(candidateWord, candidate);
	        	   
	        	   if(distance>bigDistance) 
	        		   
	        		    bigDistance = distance;
	            
	           }
			}
			
			listOfCandidatesWithDistance.put(parameter, new Candidate(bigDistance, bigOcc));
			
			return listOfCandidatesWithDistance;
	    }
	    
	
	   public  String bestCondidate(Map<String, Candidate> list,int bigOcc,int bigDistance) {
			    
			 return this.bestCandidateSelector.bestCondidate(list, bigOcc, bigDistance);
	   
	   }
			
	
	
}
