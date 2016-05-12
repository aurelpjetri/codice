package grafo;

public class DirectedEdge implements Edge{

	private Node source;
	private Node target;
	private int distance;
	private int width;
	private int weight;
	
	//metodo statico usato per validare i parametri prima della costruzione del nodo
	public static void validateNodeParameters( int w, int h){
		
		if(w<1){
			throw new RuntimeException("Illegal value of parameter 'w': "+w);
		}
		if(h<1){
			throw new RuntimeException("Illegal value of parameter 'h': "+h);
		}
	}
	
	
	public DirectedEdge(Node s, Node d, int w, int weight){
		this.source = s;
		this.target = d;
		distance = (int) Math.sqrt((s.getX()-d.getX())*(s.getX()-d.getX()) 
				+ (s.getY() - d.getY())*(s.getY() - d.getY()));
		width = w;
		this.weight = weight;
		
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
		distance = (int) Math.sqrt((source.getX()-target.getX())*(source.getX()-target.getX()) 
				+ (source.getY() - target.getY())*(source.getY() - target.getY()));
	}

	public Node getTarget() { 
		return target;
	}
	
	public void setTarget(Node dest) {
		this.target = dest;
		distance = (int) Math.sqrt((source.getX()-target.getX())*(source.getX()-target.getX()) 
				+ (source.getY() - target.getY())*(source.getY() - target.getY()));
	}

	public int getDistance() {
		return distance;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public String toString(){
		return getSource().getId()+"->"+getTarget().getId();
	}
}
