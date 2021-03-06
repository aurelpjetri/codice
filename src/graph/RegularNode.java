package graph;

import java.io.IOException;
import java.util.*;

import visitor.Visitor;

public class RegularNode implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	private int radius;
	private HashMap<Integer,Integer> state;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters(int x, int y, int w, int h, int r, HashMap<Integer,Integer> state ){
		if(x<0){
			throw new RuntimeException("Illegal value of parameter 'x': "+x);
		}
		if(y<0){
			throw new RuntimeException("Illegal value of parameter 'y': "+y);
		}
		if(w<1){
			throw new RuntimeException("Illegal value of parameter 'w': "+w);
		}
		if(h<1){
			throw new RuntimeException("Illegal value of parameter 'h': "+h);
		}
		if(r<1){
			throw new RuntimeException("Illegal value of parameter 'r': "+r);
		}
		for(int key : state.keySet()){
			if(state.get(key)<0){
				throw new RuntimeException("Illegal value of members quantity for behavior :"+key);
			}
		}
	}
	
	
	public RegularNode( int id, int x, int y, int w, int h, int r, HashMap<Integer,Integer> state){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x= x;
		this.y = y; 
		this.width = w;
		this.height = h;
		capacity = calculateCapacity(); 
		this.radius = r;
		this.state = state;

	}
	
	public HashMap<Integer,Integer> getState(){
		return state;
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
	
	public int getRadius(){
		return radius;
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
	
	public void accept(Visitor visitor) throws IOException{
		visitor.visit(this);
	}
}
