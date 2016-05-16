package grafo;

import java.io.IOException;
import java.util.List;

import visitor.Visitor;

public interface Node {

	public int getId();
	
	public int getCapacity();
	
	public List<Node> getReachableNodes();
	
	public int getX();
	
	public int getY();
	
	public int getWidth();
	
	public int getHeight();
	
	public int getRadius();
	
	public String getType();

	public void changeReachable(Node old, Node neW);

	public void addReachable(Node n);
	
	public String toString();
	
	public void accept(Visitor v) throws IOException;
//	
	public int getStatus();
//	
//	public void setStatus(int s);
	
}
