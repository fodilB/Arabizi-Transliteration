package com.esi.arabizi.transliterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class WordGenerator {
	


	List<String> specialCaractere;//Traitement with remplacer 2
	
	 Map<String,ArrayList<String>> mad7orrof,ambiguous7orrof ;
	
	 List<String> ha2,sin,dal,dha2,kaf,alif,alifHamza,ta2,ra2,ta2marbota,waw,ya2,alifMa9soura ;	
	
	/*Collection initializer*/
	
	public  void initialise() {
		  
		alif.add("");
		  
		waw.add("");
		   
		ya2.add("");
		   
		   
		ha2.add("ح");
		   
		sin.add("ص");
		   
		kaf.add("ق");
		   
		ta2.add("ط");
		   
		ra2.add("غ");
		
		alifMa9soura.add("ي");
		
	//More Than One letter Remplacement Possibility ==> د ظ ة
		   
		dal.add("ض");
		   
		dal.add("ظ");
		   
		dal.add("ذ");
		    
	    dha2.add("ض");
		   
		dha2.add("ذ");
		      
		ta2marbota.add("ا");
		   
		ta2marbota.add("ى");
		   
		alifHamza.add("ع");
			  
		alifHamza.add("ا");
		   
		   
		   ambiguous7orrof.put("ا", (ArrayList<String>)alif);
		   
		   mad7orrof.put("ا", (ArrayList<String>)alif);
		   
		   ambiguous7orrof.put("و", (ArrayList<String>)waw);
		   
		   mad7orrof.put("و", (ArrayList<String>)waw);
		   
		   ambiguous7orrof.put("ي", (ArrayList<String>)ya2);
		   
		   mad7orrof.put("ي", (ArrayList<String>)ya2);
		   
		   ambiguous7orrof.put("أ", (ArrayList<String>)alifHamza);
		   
		   ambiguous7orrof.put("ى", (ArrayList<String>)alifMa9soura);
		   
		   ambiguous7orrof.put("ه",(ArrayList<String>) ha2);
		   
		   ambiguous7orrof.put("ك",(ArrayList<String>) kaf);
		   
		   ambiguous7orrof.put("س",(ArrayList<String>) sin);
		   
		   ambiguous7orrof.put("د",(ArrayList<String>) dal);
		   
		   ambiguous7orrof.put("ظ",(ArrayList<String>) dha2);
		   
		   ambiguous7orrof.put("ت",(ArrayList<String>) ta2);
		   
		   ambiguous7orrof.put("ر",(ArrayList<String>) ra2);
		   
		   ambiguous7orrof.put("ة", (ArrayList<String>)ta2marbota);
		   
		   
		   specialCaractere.add("د");
		   
		   specialCaractere.add("ظ");
		   
		   specialCaractere.add("ة");
		   
		   specialCaractere.add("أ");
	}
	 
	 public WordGenerator() {
		 
			specialCaractere = new ArrayList<String>();
			
			mad7orrof = new HashMap<String, ArrayList<String>>();
			
			ambiguous7orrof = new HashMap<String, ArrayList<String>>();
			
			ha2 =  new ArrayList<String>();
			
			sin =  new ArrayList<String>();
			
			dal =  new ArrayList<String>();
			
		    dha2 =  new ArrayList<String>();
			
			kaf =  new ArrayList<String>();
			
			alif = new ArrayList<String>();
			
			alifHamza = new ArrayList<String>();
			
			alifMa9soura= new ArrayList<String>();
			
			ta2 = new ArrayList<String>();
			
		    ra2 = new ArrayList<String>();
			
			ta2marbota = new ArrayList<String>();
			
		    waw =  new ArrayList<String>();

			ya2 =  new ArrayList<String>();
			
			initialise();
		 
	 }
	
	  /*Generate an array of ambiguous 7oroof position*/
	
	   public  List<Integer> ambiguous7oroofPos(String word){
		   
		   List<Integer> indexArr  = new ArrayList<Integer>();
		   
		   
		   if(word.length()==1) return indexArr;
		   
		   if(ambiguous7orrof.containsKey(""+word.charAt(0)) && !mad7orrof.containsKey(""+word.charAt(0))) 
				  
			  indexArr.add(0); 
		   
		   
		   
		   for(int i=1;i<word.length()-1;i++) {
			   
			   if(ambiguous7orrof.containsKey(""+word.charAt(i))) 
				  
				  indexArr.add(i); 
		   }
		   
		 
		   
		   if(ambiguous7orrof.containsKey(""+word.charAt(word.length()-1)) && !mad7orrof.containsKey(""+word.charAt(word.length()-1))) 
				  
			  indexArr.add(word.length()-1);
		    
		return  indexArr;
		   
	   }  
	   
       public  boolean selectMethod(String word,Integer[] res) {
    	   
    	   for(int i=0;i<res.length;i++) {
    	   
    	   if(specialCaractere.contains(""+word.charAt(res[i]))) return true;
    	   
    	   }
		return false;
    	   
       }
	   
	   public  String remplacer(String word,Integer[] res) {
		   
		   
		   StringBuilder builder = new StringBuilder();
		   
		   String mappedStr;

		   builder.append(word);
		   
		   int j = 0;
		   
		   for(int i=0;i<res.length;i++) {
			   
			   mappedStr = ambiguous7orrof.get(""+builder.charAt(res[i]-j)).get(0);

			   builder.replace(res[i]-j, res[i]-j+1,mappedStr);
			   
			   if(mappedStr=="")j++;
		   }
		
		   return builder.toString();
		   
	   }
	   
		
		 public  Set<String> remplacer2(String word,Integer[] res) {
			   
			   StringBuilder builder = new StringBuilder();
			   
			   String mappedStr = null;
			   
			   String candidat = null;

			   builder.append(word);
			   
			   int j = 0,seize;
			   
			   ArrayList<String> wordArray = new ArrayList<>();
			   
			   wordArray.add(builder.toString());
			   
			   for(int i=0;i<res.length;i++) {
				      
				   ArrayList<String> arrAlterChar= ambiguous7orrof.get(""+builder.charAt(res[i]-j));
				   
				   seize=wordArray.size();
				    
	               for(int index=0;index < seize;index++) {
	            	   
	            	   candidat = wordArray.get(index);
				   
				       for(int k=0;k<arrAlterChar.size();k++) {
						   
						        mappedStr = arrAlterChar.get(k);
			                	
			                	builder.setLength(0);
			                	 	
			                	builder.append(candidat);
			                	
			                	builder.replace(res[i]-j, res[i]-j+1,mappedStr);	                	
			                	
			                	wordArray.add(builder.toString());
			                	
			                }
				                       	   	   
				   }
				   
				   for(int l=0;l<seize;l++) wordArray.remove(0);
				   
				   if(mappedStr == "") j++;	
				   
			   }
			   
			   Set<String> set = new HashSet<String>(wordArray);
			   
			   wordArray.clear();
			  
			   return  set;
		   }
		 
		   private static int minimum(int a, int b, int c) {      
			   
		        return Math.min(Math.min(a, b), c);                                      
		    } 
		 
		    public  int levenshteinDistance(String lhs, String rhs) {      
		       
		    	int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];        
		                                                                                 
		        for (int i = 0; i <= lhs.length(); i++)                                 
		            distance[i][0] = i;                                                  
		        
		        for (int j = 1; j <= rhs.length(); j++)                                 
		            distance[0][j] = j;                                                  
		                                                                                 
		        for (int i = 1; i <= lhs.length(); i++)                                 
		            for (int j = 1; j <= rhs.length(); j++)  
		            	
		                distance[i][j] = minimum(                                        
		                        
		                		distance[i - 1][j] + 1,                                  
		                
		                        distance[i][j - 1] + 1,                                  
		                        
		                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
		                                                                                 
		        return distance[lhs.length()][rhs.length()];                           
		    } 
		    
	   public  String bestCondidate(Map<String, Candidate> list,int bigOcc,int bigDistance) {
		    
		   float coefDis=0.5f;

		
		    float coeffOcc=0.5f;
		   
		  Candidate candidate;
		  
		  float indice=0;
		  
		  String selectedWord="";
		  
		   for(Map.Entry<String, Candidate> entry: list.entrySet()) {
			   
			   candidate =(Candidate) entry.getValue();
	           
			   float indiceCandidate = 100*(coefDis*(1-((float)candidate.getDistance()/(bigDistance+1)))+coeffOcc*((float)candidate.getOccurence()/bigOcc));
	           
	           if(indiceCandidate >= indice) { 
	        	   
	        	   indice = indiceCandidate;
	        	   
	        	   selectedWord=entry.getKey();
	        	   
	           }
	           
	          System.out.println(entry.getKey()+" , D="+candidate.getDistance()+" , Occ="+candidate.getOccurence());
			}
		   
		   
		   return selectedWord;
		   
	   }
	  
}
