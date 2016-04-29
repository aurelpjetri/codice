package grafo;

import java.util.*;

public class RegularNode implements Node {

	private ArrayList<Node> reachables;
	private int id;
	
	public RegularNode(int id){
		reachables = new ArrayList<Node>();
		this.id = id;
	}
	
	public void addReachable(Node n){
		reachables.add(n);
	}
	
	public List<Node> getReachableNodes(){
		return reachables;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void changeReachable(Node old, Node neW){
		reachables.remove(old);
		reachables.add(neW);
		
	}
}
