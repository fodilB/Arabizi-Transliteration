package com.esi.arabizi.transliterator;

import java.util.Map;

import com.esi.arabizi.tools.Distance;

public class BestCandidate {

	private Distance distance;
	
	private float coefficientDistance;
	
	private float coefficientOccurence;
	
	public BestCandidate(float coefficientOccurence,float coefficientDistance,Distance distance) {
		
		this.setDistance(distance);
		
		this.coefficientDistance=coefficientDistance;

		this.coefficientOccurence=coefficientOccurence;
	}
	
	public int computeDistance(String word , String referenceWord) {
		
		return this.distance.computeDistance(word, referenceWord);
	}
	
	public  String bestCondidate(Map<String, Candidate> list,int bigOcc,int bigDistance) {

		Candidate candidate;
			  
		float indice=0;
			  
		String selectedWord="";
			  
		for(Map.Entry<String, Candidate> entry: list.entrySet()) {
				   
			candidate =(Candidate) entry.getValue();
		           
			float indiceCandidate = 100*(coefficientDistance*(1-((float)candidate.getDistance()/(bigDistance+1)))+coefficientOccurence*((float)candidate.getOccurence()/bigOcc));
		           
		     if(indiceCandidate >= indice) { 
		        	   
		        indice = indiceCandidate;
		        	   
		        selectedWord=entry.getKey();
		        	   
		      }
		           
		         // System.out.println(entry.getKey()+" , D="+candidate.getDistance()+" , Occ="+candidate.getOccurence());
		 }
			   
			   
	  return selectedWord;
			   
    }
	
	public Distance getDistance() {
		return distance;
	}

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public float getCoefficientDistance() {
		return coefficientDistance;
	}

	public void setCoefficientDistance(float coefficientDistance) {
		this.coefficientDistance = coefficientDistance;
	}

	public float getCoefficientOccurence() {
		return coefficientOccurence;
	}

	public void setCoefficientOccurence(float coefficientOccurence) {
		this.coefficientOccurence = coefficientOccurence;
	}
	
	
}
