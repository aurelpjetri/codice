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
	
	
	//checks fist the existence of the edge in the list of edges 
	//then checks if a link between the nodes already exists
	public void addEdge(Edge e){
		boolean exists = false;
		if(e instanceof DirectedEdge){
			if(!validateEdge(e) && !validateEdge(e.getSource(), e.getTarget())){
				exists = false;
			}
		}
		else{
			if(!validateEdge(e) 
					&& !validateEdge(e.getSource(), e.getTarget()) 
					&& validateEdge(e.getTarget(), e.getSource())){
				
				exists = false;
			
			}
		}
		
		if(exists){
			throw new RuntimeException("edge already existing");
		}
		else{ 
			edges.add(e); 
		}
	}
	
	public Node getNode(int id){
	
		for(Node node : nodes){
			if(node.getId() == id){
				return node;
			}
		}
		throw new RuntimeException("node doesent exist");

	}
	
	public Node getNode(int x, int y){
		for(Node node: nodes){
			if(node.getX() == x && node.getY() == y)
				return node;
		}
		throw new RuntimeException("node x, y, doesent exist");
	}

	public ArrayList<Node> getNodes(){
		return this.nodes;
	}
	
	public ArrayList<Edge> getEdges(){
		return this.edges;
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
	
	public boolean validateEdge(Node s, Node t){
		for(Edge e : edges){
			if(e.getSource().equals(s) && e.getTarget().equals(t)){
				return true;
			}
			if(e.getSource().equals(t) && e.getTarget().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	public boolean validateNodeFromid(int id){
		for(Node node: nodes){
			if(node.getId() == id){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		
		String output = "";
		
		for(Node node:nodes){
			output += "node: "+node.toString()+ " reachables:";
			for(Node r : node.getReachableNodes()){
				output +=+ r.getId()+" ";
			}
		}
		
//		for(Edge edge: edges){
//			if(edge instanceof DirectedEdge){
//				System.out.println(edge.getSource().getId()+"--->"
//						+edge.getTarget().getId()+"  distance: "+edge.getDistance());
//			}
//			else{
//				System.out.println(edge.getSource().getId()+"----"
//						+edge.getTarget().getId()+"  distance: "+edge.getDistance());
//			}
//		}
		
		return output;
	}

}
