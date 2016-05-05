package grafo;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	
	public EntryPoint(int id, int x, int y){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public String getType(){
		return "entry";
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
	
	public void generateActor(){
		//genera attori
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public String toString(){
		String output = "";
		output += getId() + " entry";
		return output;
	}
}
