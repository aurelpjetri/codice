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
	private HashMap<Integer, Float> generationPercentage;
	private float entryRate;
	private int entryLimit;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters(int x, int y, int w, int h, int r, float entryR, HashMap<Integer,Integer> state, HashMap<Integer, Float> generationpPercentage){
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
		if(entryR<0 || entryR>1){
			throw new RuntimeException("Illegal value of parameter 'entry-rate': "+entryR);
		}
		for(int key : state.keySet()){
			if(state.get(key)<0){
				throw new RuntimeException("Illegal value of members quantity for behavior :"+key);
			}
		}
		float sum = 0; 
		for(int key : generationpPercentage.keySet()){
			if(generationpPercentage.get(key)<0){
				throw new RuntimeException("Illegal value of generation percentage for behavior :"+key);
			}
			sum += generationpPercentage.get(key);
		}
		if(sum != 1){
			throw new RuntimeException("percentage sum must be equal to 1, not: "+sum);
		}
	}
	
	public EntryPoint(int id, int x, int y, int w, int h, int r, float entryRate, HashMap<Integer,Integer> state, HashMap<Integer, Float> generationPercentage, int entryLimit){
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
		this.generationPercentage = generationPercentage;
		this.entryRate = entryRate;
		this.entryLimit = entryLimit;
	}
	
	public int getEntryLimit(){
		return entryLimit;
	}
	
	public float getEntryRate(){
		return entryRate;
	}
	
	public HashMap<Integer, Float> getGenerationPercentage(){
		return generationPercentage;
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
