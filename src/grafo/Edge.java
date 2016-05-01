package grafo;

public class Edge {

	private Node source;
	private Node target;
	private int weight;
	
	public Edge(Node s, Node d){
		this.source = s;
		this.target = d;
		this.weight = 1;
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

	public void setWeight(int w){
		this.weight = w;
	}

	public int getWeight() {
		return weight;
	}
	
	
	
	
}
