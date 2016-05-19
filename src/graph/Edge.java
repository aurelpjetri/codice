package graph;

import java.io.IOException;

import visitor.Visitor;

public interface Edge {

	public Node getSource();
	
	public void setSource(Node source);
	
	public Node getTarget();
	
	public void setTarget(Node target);
	
	public int getDistance();
	
	public int getWidth();
	
	public int getWeight();
	
	public void accept(Visitor visitor)throws IOException;
	
}
