package grafo;

import java.util.ArrayList;
import java.util.List;

public class ExitPoint implements Node{

	private ArrayList<Node> reachables;
	private int id;
	private int capacity;
	
	public ExitPoint(int id){
		reachables = new ArrayList<Node>();
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void sinkActor(){
		//sinks actor
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
//	public void addReachable(Node n){
//		System.out.println("Can't add reachables to an Exit Point");
//	}
//	
	public List<Node> getReachableNodes(){
		return reachables;
	}
//	
//	
//	public void changeReachable(Node old, Node neW){
//		//do nothing
//		
//	}
	

	
}
