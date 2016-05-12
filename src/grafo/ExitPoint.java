package grafo;

import java.util.ArrayList;
import java.util.List;



public class ExitPoint implements Node{

	private ArrayList<Node> reachables;
	private int id;
	private int x, y;
	private int capacity;
	private int width, height;
	private int radius;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters(int x, int y, int w, int h, int r){
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
	}
	
	public ExitPoint(int id, int x, int y, int w, int h, int r){
		reachables = new ArrayList<Node>();
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		//supponendo che le misure sono in metri e che in un metro quadrato ci stanno 5 persone
		capacity = calculateCapacity();
		this.radius = r;
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
	
}
