package behaviors;

import java.io.IOException;
import java.util.List;

import graph.Node;
import visitor.Visitor;

public interface Behavior {
	
	public int getId();

	public List<Node> getCoreInterestPoints();
	
	public List<Node> getOptionalInterestPoints();
	
	public void addOptionalInterestPoint(Node n);

	public void addCoreInterestPoint(Node n);
	
	public void accept(Visitor v)throws IOException;
	
}
