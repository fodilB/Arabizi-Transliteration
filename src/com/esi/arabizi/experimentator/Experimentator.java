package com.esi.arabizi.experimentator;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import com.esi.arabizi.tools.DataLoader;
import com.esi.arabizi.tools.Distance;
import com.esi.arabizi.tools.LevenshteinDistance;
import com.esi.arabizi.transliterator.Arabizi2arabic;
import com.esi.arabizi.transliterator.BestCandidate;
import com.esi.arabizi.transliterator.Transliterator;

public class Experimentator {
	
	public static void main(String[] args) throws IOException {
		
		long startTime = System.currentTimeMillis();
		
		//Chargement des donnes
		System.out.println("Loading Data :"); 
		
		DataLoader dataLoader = new DataLoader();
		
		Map<String, Integer> wordFrequencyMap =  dataLoader.chargementDuModeleDelangue();
		
		System.out.println("Donné Chargé avec Succé ..."+(float) (System.currentTimeMillis()-startTime)/1000 +"s");
		
		startTime = System.currentTimeMillis();
		
		
		//Transliteration des corpus
		int corpusSize = 300; 
		
		String src1 = "test_corpus/"+corpusSize+"/src_"+corpusSize+"_arabizi.txt";
		
		String dst  = "test_corpus/"+corpusSize+"/dst_"+corpusSize;
		
		System.out.println("\nTransliteration du fichier "+src1+":\n");
		
		Arabizi2arabic arabiziConverter = Arabizi2arabic.getInstance();

		arabiziConverter.converter(src1, dst);
		
		System.out.println("la Phase 1 du translitération du corpus : "+corpusSize+ " est terminé avec succé..."+(float) (System.currentTimeMillis()-startTime)/1000 +"s");
	
		String translatedFile  = "test_corpus/"+corpusSize+"/corpus_"+corpusSize+"_transliterated";
		
		float coefficientOccurence = 0.7f;
		
		float coefficientDistance  = 0.4f;
		
		Distance distance = new LevenshteinDistance();
		
		BestCandidate bestCandidateSelector = new BestCandidate(coefficientOccurence, coefficientDistance, distance);
		
		Transliterator transliterator = new Transliterator(dst,translatedFile,wordFrequencyMap,bestCandidateSelector);
	
		transliterator.transliterer();
		
		System.out.println("la Phase 2 du translitération du fichier : "+corpusSize+ " est terminé avec succé..."+(float) (System.currentTimeMillis()-startTime)/1000 +"s");
	
	    
		//Evaluation 
		
		System.out.println("\nEvaluation de la Transliteration  du fichier :"+src1+":\n");
		
		String referenceFile = "test_corpus/"+corpusSize+"/src_"+corpusSize+"_arabic.txt";
		
		Evaluator evaluator = new Evaluator();
		
		//test_corpus/50/translatedCorpus
		
		float accuracy = evaluator.Accuracy(translatedFile, referenceFile);
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		System.out.println("Accuracy : "+df.format(accuracy)+"%");
	
	}

}
