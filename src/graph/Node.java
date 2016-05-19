package graph;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import visitor.Visitor;

public interface Node {

	public int getId();
	
	public int getCapacity();
	
	public int calculateCapacity();
	
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

	public HashMap<Integer,Integer> getState();
	
	public HashMap<Integer, Float> getSinkingRate();
	
	public HashMap<Integer, Float> getGenerationRate();
	
}
