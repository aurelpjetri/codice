package grafo;

public class Edge {

	private Node source;
	private Node target;
	
	public Edge(Node s, Node d){
		this.source = s;
		this.target = d;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDest() {
		return target;
	}

	public void setDest(Node dest) {
		this.target = dest;
	}

	
	
	
}
