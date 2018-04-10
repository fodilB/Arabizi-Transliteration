package com.esi.arabizi.transliterator;

public class Candidate {
	
	private int distance;
	
	private int occurence;
	
	public Candidate(int distance,int occurence) {
		
		this.distance = distance;
		
		this.occurence = occurence;
		
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}
	

}
