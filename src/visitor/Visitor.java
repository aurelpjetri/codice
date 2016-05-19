package visitor;

import java.io.IOException;

import behaviors.Behavior;
import graph.DirectedEdge;
import graph.EntryPoint;
import graph.ExitPoint;
import graph.Graph;
import graph.RegularNode;
import graph.UndirectedEdge;

public interface Visitor {
	
	public void visit(Graph g) throws IOException;
	
	public void visit(RegularNode n)throws IOException;
	
	public void visit(EntryPoint n)throws IOException;

	public void visit(ExitPoint n)throws IOException;
	
	public void visit(UndirectedEdge e)throws IOException;
	
	public void visit(DirectedEdge e)throws IOException;
	
	public void visit(Behavior b)throws IOException;
	
}
