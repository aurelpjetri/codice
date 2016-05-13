package visitor;

import java.io.IOException;

import grafo.DirectedEdge;
import grafo.EntryPoint;
import grafo.ExitPoint;
import grafo.Graph;
import grafo.RegularNode;
import grafo.UndirectedEdge;

public interface Visitor {
	
	public void visit(Graph g) throws IOException;
	
	public void visit(RegularNode n)throws IOException;
	
	public void visit(EntryPoint n)throws IOException;

	public void visit(ExitPoint n)throws IOException;
	
	public void visit(UndirectedEdge e)throws IOException;
	
	public void visit(DirectedEdge e)throws IOException;
	
}
