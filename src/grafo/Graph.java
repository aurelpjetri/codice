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
	public void addEdge(Edge e){
		if(validateEdge(e.getSource(), e.getTarget() ) ){
			throw new RuntimeException("edge already existing");
		}
		else{
			edges.add(e); 
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
	
	public boolean validateNode(int id){
		for(Node node: nodes){
			if(node.getId() == id){
				return true;
			}
		}
		return false;
	}
	
	public boolean validateNode(int x, int y){
		for(Node node: nodes){
			if(node.getX() == x && node.getY() == y){
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
