package grafo;

import java.io.IOException;

import visitor.Visitor;

public interface Edge {

	public Node getSource();
	
	public void setSource(Node source);
	
	public Node getTarget();
	
	public void setTarget(Node target);
	
	public int getDistance();
	
	public int getWidth();
	
	public void accept(Visitor visitor)throws IOException;
	
}
