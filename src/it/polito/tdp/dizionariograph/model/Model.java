package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	
	Graph<String,DefaultEdge> graph;
	private List<String>parole;
	private WordDAO w;
	private int numeroLettere;
	
	public Model() {
		
		
		graph=new SimpleWeightedGraph<>(DefaultEdge.class);
		
        w=new WordDAO();
		
       
		
	}

	public void createGraph(int numeroLettere) {
		
		parole=w.getAllWordsFixedLength(numeroLettere);
		this.numeroLettere=numeroLettere;
		
		Graphs.addAllVertices(graph,parole);
		
	
	}
	public static List<String> getAllSimilarWords(List<String> parole, String parola, int numeroLettere) {

		List<String> paroleSimili = new ArrayList<String>();
		for (String p : parole) {
			if (adiacenzaValida(parola, p))
				paroleSimili.add(p);
		}

		return paroleSimili;
	}

	public List<String> displayNeighbours(String parolaInserita) {
		
		for (String parola :parole) {

			

			List<String> paroleSimili = getAllSimilarWords(parole, parola,numeroLettere);

			for (String parolaSimile : paroleSimili) {
				graph.addEdge(parola, parolaSimile);
			}
		}

	  List<String> result=new LinkedList<String>();
	  
      result.addAll(Graphs.neighborListOf(graph,parolaInserita));
	  
	  return result;
	  
	  
	  }
	  
	  
	 	
		

		

		
	public static boolean adiacenzaValida(String first, String second) {

		if (first.length() != second.length())
			throw new RuntimeException("Le due parole hanno una lunghezza diversa.");

		int distance = 1;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i))
				distance--;
		}

		if (distance == 0)
			return true;
		else
			return false;
	}





	

	public int findMaxDegree() {
		
		
		int max=0;
		for(String s:graph.vertexSet()) {
			
			if(graph.degreeOf(s)>max)
				max=graph.degreeOf(s);
			
			
			
			
		}
		
		
		return max;
	}
}
