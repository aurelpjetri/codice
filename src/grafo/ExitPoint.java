package grafo;

import java.util.ArrayList;
import java.util.List;

public class ExitPoint implements Node{

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	
	public ExitPoint(int id, int x, int y){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public String getType(){
		return "exit";
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void sinkActor(){
		//sinks actor
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public void addReachable(Node n){
	}
	
	public List<Node> getReachableNodes(){
		return reachables;
	}
	
	public void changeReachable(Node old, Node neW){
		
	}
	
	public String toString(){
		String output = "";
		output += getId() + " exit";
		return output;
	}
	
}
