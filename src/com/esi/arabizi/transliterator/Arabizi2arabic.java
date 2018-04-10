package com.esi.arabizi.transliterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import com.esi.arabizi.tools.FileManipulator;

/**
 * @Prétraitement:
 * Eliminer les doublons
 * Enlever la ponctuation
 */

/*Singleton*/

public class Arabizi2arabic {
	
		private BufferedReader fileReader = null;
		
		private PrintWriter    fileWriter = null;
		
		private FileManipulator fileManipulator;
		
		private static Arabizi2arabic instance = new Arabizi2arabic();
				  
		private final String[] latinLettres2 = {
			
	    /*0*/"^ma[c|s]ha$","^al?[l]ah$","^lel","oua","oia",
	    
	    /*1*/"oi","ge","hom$","hem$","hum$",
	    
		/*2*/"him$","kom$","kem$","kum$","cha$",
		
		/*3*/"ch","sh","kh","dh","th",
		
	    /*4*/"gh","^ou$","^ou", //ha$,"na$"
	    
	    /*5*/"ou","dj","^al","ph","ci",
	    
	    /*6*/"ce","ia","^a","aa$","a$",
	    
	    /*7*/"a","b","c","d","^e",
	    
		/*8*/"e$","e","f","g","h",
		
		/*9*/"^i","i","j","k","l",
		
		/*10*/"m","n","^o","o","p",
		
		/*11*/"q","r","s","t","u",
		
		/*12*/"v","^w","w$","w","y",
		
		/*13*/"z","2","3","5","6",
		
		/*14*/"7","9","x"};
		
		private final String[] arabicChars2 = {
				 
		  /*0*/"\u0645\u0627\u0020\u0634\u0627\u0621","\u0627\u0644\u0644\u0647","\u0644\u0644","\u0648","\u0648",
		  
		  /*1*/"\u0648","\u062C","\u0647\u0645","\u0647\u0645","\u0647\u0645",
		  
		  /*2*/"\u0647\u0645","\u0643\u0645","\u0643\u0645","\u0643\u0645","\u0634\u0627",
		  
		  /*3*/"\u0634","\u0634","\u062E","\u0638","\u062B",
		  
		  /*4*/"\u063A","\u0648","\u0627", //,"\u0647\u0627","\u0646\u0627"
		  
		  /*5*/"\u0648","\u062C","\u0623\u0644","\u0641","\u0633\u0623",
		  
		  /*6*/"\u0633","\u064A\u0627","\u0623","\u0627\u0621","\u0629",
		  
		  /*7*/"\u0627","\u0628","\u0643","\u062F","\u0627",
		  
		  /*8*/"","\u0627","\u0641","\u0642","\u0647",
		  
		  /*9*/"\u0627","\u064A","\u062C","\u0643","\u0644",
		  
		  /*10*/"\u0645","\u0646" ,"\u0627","\u0648","\u0628",
		  
		  /*11*/"\u0643","\u0631","\u0633","\u062A","\u0648",
		  
		  /*12*/"\u0641","\u0648","\u0648","\u0648","\u0649",
		  
		  /*13*/"\u0632","\u0627","\u0639","\u062E","\u0637",
		  
		  /*14*/"\u062D","\u0642","\u0643\u0633"};
		
		
		
		private Arabizi2arabic() {
			
			this.fileManipulator = new FileManipulator();
			
		}
		
		
	  public static Arabizi2arabic getInstance() {
			
			      return instance;
	    }
		
		
	   public void converter(String srcFileName, String dstFileName) throws IOException {
		   
		   this.fileInitializer(srcFileName , dstFileName);
		   
		   this.arabiziConverter();
	   }
		
	   
       private void fileInitializer(String srcFileName, String dstFileName) {
	    	
	    fileReader = this.fileManipulator.fileReader(srcFileName);
  	
	    fileWriter = this.fileManipulator.fileWriter(dstFileName);

       }
	    
	        
       private void arabiziConverter() throws IOException {
		    	
			String ligne="";  
			    
			String word=""; 
		    
		    while ((ligne = fileReader.readLine()) != null) {
		    	
		    	StringTokenizer token = new StringTokenizer(ligne," ");
		    	
				while (token.hasMoreTokens()) {
					   
					word = token.nextToken();
					
					word=word.toLowerCase();
					   
				    if(!word.chars().allMatch( Character::isDigit ))
						
					for(int i=0;i<latinLettres2.length;i++)
							
						word = word.replaceAll(latinLettres2[i], ""+arabicChars2[i]);
							
					fileWriter.print(word+" ");	
		
				}
				
				fileWriter.println("");
								   		    
			}				
						    
			fileReader.close();
			
			fileWriter.close();	
			
		}

			    	
}