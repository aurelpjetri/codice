package grafo;

public class DirectedEdge implements Edge{

	private Node source;
	private Node target;
	private int distance;
	
	public DirectedEdge(Node s, Node d){
		this.source = s;
		this.target = d;
		distance = (int) Math.sqrt((s.getX()-d.getX())*(s.getX()-d.getX()) 
				+ (s.getY() - d.getY())*(s.getY() - d.getY()));
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
	
}
