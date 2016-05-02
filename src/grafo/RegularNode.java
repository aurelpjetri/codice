package grafo;

import java.util.*;

public class RegularNode implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	
	public RegularNode( int id, int x, int y){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x= x;
		this.y = y; 
	}
	
	public String getType(){
		return "normal";
	}
	public void addReachable(Node n){
		reachables.add(n);
	}
	
	public List<Node> getReachableNodes(){
		return reachables;
	}
		
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getId(){
		return this.id;
	}
	
	public void changeReachable(Node old, Node neW){
		reachables.remove(old);
		reachables.add(neW);
	}
	
	public int getCapacity(){
		return this.capacity;
	}
}
