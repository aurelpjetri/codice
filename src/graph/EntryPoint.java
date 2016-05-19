package graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import visitor.Visitor;

public class EntryPoint implements Node {

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	private int radius;
	private HashMap<Integer,Integer> state;
	private HashMap<Integer, Float> generationRate;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters(int x, int y, int w, int h, int r, HashMap<Integer,Integer> state, HashMap<Integer, Float> generationRate){
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
		for(int key : generationRate.keySet()){
			if(state.get(key)<0){
				throw new RuntimeException("Illegal value of generation rate for behavior :"+key);
			}
		}
		
	}
	
	public EntryPoint(int id, int x, int y, int w, int h, int r, HashMap<Integer,Integer> state, HashMap<Integer, Float> generationRate){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
		this.capacity = calculateCapacity();
		this.radius = r;
		this.state = state;
		this.generationRate = generationRate;
	}
	
	public HashMap<Integer, Float> getGenerationRate(){
		return generationRate;
	}
	
	public HashMap<Integer, Float> getSinkingRate(){
		return null;
	}
	
	public HashMap<Integer,Integer> getState(){
		return state;
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
	
	public int getRadius(){
		return radius;
	}
	
	public void changeReachable(Node old, Node neW){
		reachables.remove(old);
		reachables.add(neW);
		
	}

	
	public int getCapacity(){
		return this.capacity;
	}
	
	//metodo per il calcolo della capacità dell'incrocio.
	//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
	public int calculateCapacity(){
		return (width * height * 5);
	}
	
	public String toString(){
		return getId()+": entry";
	}
	
	public void accept(Visitor visitor) throws IOException{
		visitor.visit(this);
	}
}
