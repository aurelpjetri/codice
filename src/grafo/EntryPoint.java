package grafo;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint {

	private ArrayList<Node> reachables;
	private int id;
	private int capacity;
	
	public EntryPoint(int id){
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
	
	public void generateActor(){
		//genera attori
	}
	
	public int getCapacity(){
		return this.capacity;
	}
}
