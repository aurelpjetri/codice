package graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import visitor.Visitor;



public class ExitPoint implements Node{

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	private int radius;
	private HashMap<Integer,Integer> state;
	private HashMap<Integer, Float> sinkingPercentage;
	private float exitRate;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters(int x, int y, int w, int h, int r, float exitR, HashMap<Integer,Integer> state, HashMap<Integer, Float> sinkingRate){
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
		if(exitR<0 || exitR>1){
			throw new RuntimeException("Illegal value of parameter 'exit-rate': "+exitR);
		}
		float sum = 0; 
		for(int key : sinkingRate.keySet()){
			if(sinkingRate.get(key)<0){
				throw new RuntimeException("Illegal value of generation percentage for behavior :"+key);
			}
			sum += sinkingRate.get(key);
		}
		if(sum != 1){
			throw new RuntimeException("percentages sum must be equal to 1, not: "+sum);
		}
		for(int key : sinkingRate.keySet()){
			if(sinkingRate.get(key)<0){
				throw new RuntimeException("Illegal value of sinking rate for behavior :"+key);
			}
		}
	}
	
	
	public ExitPoint(int id, int x, int y, int w, int h, int r, float exitRate, HashMap<Integer,Integer> state, HashMap<Integer, Float> sinkingRate){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.capacity = calculateCapacity();
		this.radius = r;
		this.state = state;
		this.sinkingPercentage = sinkingRate;
		this.exitRate = exitRate;
	}
	
	public float getExitRate(){
		return exitRate;
	}
	
	public HashMap<Integer, Float> getSinkingPercentage(){
		return sinkingPercentage;
	}
	
	public HashMap<Integer, Float> getGenerationPercentage(){
		return null;
	}
	
	public HashMap<Integer, Integer> getState(){
		return state;
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
	}
	
	public List<Node> getReachableNodes(){
		return reachables;
	}
	
	public void changeReachable(Node old, Node neW){
		
	}
	
	//metodo per il calcolo della capacità dell'incrocio.
	public int calculateCapacity(){
		//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
		return (width * height * 5); 
	}
	
	public String toString(){
		return getId()+": exit";
	}
	
	public void accept(Visitor visitor) throws IOException{
		visitor.visit(this);
	}
}
