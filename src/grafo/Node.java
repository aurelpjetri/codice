package grafo;

import java.util.*;

public interface Node {

	public int getId();
	
	public List<Node> getReachableNodes();
	
	public void changeReachable(Node old, Node neW);

	public void addReachable(Node n);
	
}
