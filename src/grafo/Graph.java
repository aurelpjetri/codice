package grafo;

import java.util.ArrayList;


public class Graph {
	
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public Graph(){
		this.nodes = new ArrayList<Node>();
		this.edges= new ArrayList<Edge>();
	}
	
	public void addNode(Node n){
		nodes.add(n);
	}
	
	public void addEdge(Edge e){
		edges.add(e);
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public boolean validateNode(Node n){
		
		if(nodes.contains(n)){
			return true;
			}
		return false;
	}
	
	public boolean validateEdge(Edge e){
		
		if(edges.contains(e)){
			return true;
		}
		
		return false;
	}
	

}
