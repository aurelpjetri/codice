package behaviors;

import java.util.List;

import grafo.Node;

public interface Behavior {
	
	public int getId();

	public List<Node> getCoreInterestPoints();
	
	public List<Node> getOptionalInterestPoints();
	
	public void addOptionalInterestPoint(Node n);

	public void addCoreInterestPoint(Node n);
	
}
