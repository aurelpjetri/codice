package grafo;

import java.util.List;

public interface Node {

	public int getId();
	
	public int getCapacity();
	
	public List<Node> getReachableNodes();
	
	public int getX();
	
	public int getY();
	
	public String getType();
//	
	public void changeReachable(Node old, Node neW);
//
	public void addReachable(Node n);
	
	public String toString();
	
	
}
