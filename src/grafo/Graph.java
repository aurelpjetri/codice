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
	
	
	//checks if a link between the nodes already exists
	public void addEdge(Edge e) throws RuntimeException{
		if(validateEdge(e.getSource(), e.getTarget() ) ){
			throw new RuntimeException(" the edge between"+e.getSource().getId()+" and "+e.getTarget().getId()+" already exists");
		}
		else{
			edges.add(e);
			//devo aggiornare la lista dei reachables nei nodi coinvolti
			if(e instanceof DirectedEdge){
				e.getSource().addReachable(e.getTarget());
			}
			else{
				e.getSource().addReachable(e.getTarget());
				e.getTarget().addReachable(e.getSource());
			}
		}
	}
	
	public boolean validateEdge(Node s, Node t){
		for(Edge e : edges){
			if(e instanceof UndirectedEdge){
				if(e.getSource().equals(s) && e.getTarget().equals(t)){
					return true;
				}
				if(e.getSource().equals(t) && e.getTarget().equals(s)){
					return true;
				}
			}
			else{
				if(e.getSource().equals(s) && e.getTarget().equals(t)){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Node getNode(int id)throws RuntimeException{
	
		for(Node node : nodes){
			if(node.getId() == id){
				return node;
			}
		}
		throw new RuntimeException("node "+id+" does not exist");

	}
	
	public Node getNodeFromCoordinates(int x, int y)throws RuntimeException{
		for(Node node: nodes){
			if(node.getX() == x && node.getY() == y)
				return node;
		}
		throw new RuntimeException("node "+x+" , "+y+" doesent exist");
	}

	public ArrayList<Node> getNodes(){
		return this.nodes;
	}
	
	public ArrayList<Edge> getEdges(){
		return this.edges;
	}
	
	public boolean validateNode(int id){
		for(Node node: nodes){
			if(node.getId() == id){
				return true;
			}
		}
		return false;
	}
	
	public boolean validateNodeFromCoordinates(int x, int y){
		for(Node node: nodes){
			if(node.getX() == x && node.getY() == y){
				return true;
			}
		}
		return false;
	}
	
	
	//controlla che tutti i nodi siano collegati
	public boolean validateConnectedNodes(){
		for(Node n :  nodes){
			for(Edge e  : edges){
				if( e.getTarget().equals(n) || e.getSource().equals(n) ){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public String toString(){
		
		String output = "";
		
//		for(Node node:nodes){
//			output += node.toString() + "\n";
//		}
//		
//		return output;
		
		for(Edge e: edges){
			output += e.toString() + "\n";
		}
		return output;
	}

}
