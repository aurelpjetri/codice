package grafo;

public interface Edge {

	public Node getSource();
	
	public void setSource(Node source);
	
	public Node getTarget();
	
	public void setTarget(Node target);
	
	public int getDistance();
	
	
}
