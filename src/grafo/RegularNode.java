package grafo;

import java.util.*;

public class RegularNode implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	
	public RegularNode( int id, int x, int y, int w, int h){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x= x;
		this.y = y; 
		this.width = w;
		this.height = h;
		capacity = calculateCapacity(); 
	}
	
	public List<Node> getReachableNodes(){
		return reachables;
	}
	
	public String getType(){
		return "normal";
	}
	
	public int getId(){
		return this.id;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void addReachable(Node n){
		reachables.add(n);
	}
	
	public void changeReachable(Node old, Node neW){
		reachables.remove(old);
		reachables.add(neW);
	}
	
	public String toString(){
		return getId()+": normal";
	}
	
	//metodo per il calcolo della capacità dell'incrocio.
	public int calculateCapacity(){
		//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
		return (width * height * 5); 
	}
}
