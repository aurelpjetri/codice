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
		if(!edges.contains(e)){
			edges.add(e);
		}
		else{
			throw new RuntimeException("edge already existing");
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
	
	public boolean validateNodeFromid(int id){
		for(Node node: nodes){
			if(node.getId() == id){
				return true;
			}
		}
		return false;
	}
	
	public void printGraph(){
		
		for(Node node:nodes){
			System.out.println(node.getId()+" "+node.getType());
			for(Node r : node.getReachableNodes()){
				System.out.println("	"+r.getId());
			}
		}
		
		for(Edge edge: edges){
			if(edge instanceof DirectedEdge){
				System.out.println(edge.getSource().getId()+"--->"
						+edge.getTarget().getId()+"  distance: "+edge.getDistance());
			}
			else{
				System.out.println(edge.getSource().getId()+"----"
						+edge.getTarget().getId()+"  distance: "+edge.getDistance());
			}
		}
	}

}
