package grafo;

import java.util.List;

public interface Node {

	public int getId();
	
	public int getCapacity();
	
	public List<Node> getReachableNodes();
//	
//	public void changeReachable(Node old, Node neW);
//
//	public void addReachable(Node n);
	
	
}
