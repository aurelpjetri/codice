package grafo;

import java.util.ArrayList;
import java.util.List;

public class EntryPoint implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	
	public EntryPoint(int id, int x, int y, int w, int h){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
		capacity = (width * height * 5); 
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
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
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
		return getId()+": entry";
	}
}
