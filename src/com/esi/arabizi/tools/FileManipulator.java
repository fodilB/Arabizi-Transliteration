package com.esi.arabizi.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FileManipulator {
	
	private BufferedReader fileReader = null;
	
	private PrintWriter    fileWriter = null;

	public BufferedReader fileReader(String fileName) {
		
	    try{
	    	
	    	fileReader = new BufferedReader(new FileReader(fileName));	
	    	
	    } catch (FileNotFoundException exc) {
	    	
	         System.err.println("Erreur lors de l'ouverture du fichier : "+fileName);
	    }
	    
	    return fileReader;
		
	}
	
	public PrintWriter fileWriter(String fileName) {
	    
		try {
	    	
			 fileWriter =  new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			 
		} catch (IOException e) {
			
			System.err.println("Erreur lors de l'ouverture du fichier : "+fileName);
		}
	    
	    return fileWriter;
	}
	
	public void fileToWord(String sourceFile,String destinationFile) throws IOException {
	
		BufferedReader fileReader = null;
		
		PrintWriter    fileWriter = null;
	
	    String ligne;

	    fileReader = this.fileReader(sourceFile);
	 
	    fileWriter = this.fileWriter(destinationFile);
	    
		while ((ligne = fileReader.readLine()) != null) 
		{
			
			StringTokenizer s = new StringTokenizer(ligne);
			
			while (s.hasMoreTokens())  
				 
				 fileWriter.println(s.nextToken());			    
		}
	
		fileReader.close();
		
		fileWriter.close();


	}
}
