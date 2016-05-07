package builder;

import grafo.*;
//import grafo.Edge;
import grafo.DirectedEdge;
import java.util.List;
import java.util.ArrayList;

public class ConcreteBuilder {

	private Graph product;
	private List<Integer> ids;
	
	public void buildGraph(){
		product = new Graph();
		ids = new ArrayList<Integer>();
	}
	
	public void buildRegularNode(int x, int y, int w, int h){
		ids.add(1);
		int id = ids.size();
		product.addNode(new RegularNode(id, x, y, w, h));
	}
	
	public void buildExitPoint(int x , int y, int w, int h){
		ids.add(1);
		int id = ids.size();
		product.addNode( new ExitPoint(id, x, y, w, h));
	}
	
	public void buildEntryPoint(int x , int y, int w, int h){
		ids.add(1);
		int id = ids.size();
		product.addNode( new EntryPoint(id, x, y, w, h));
	}
		
	public void buildDirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int w){
		DirectedEdge edge = new DirectedEdge(product.getNode(sourceX, sourceY),product.getNode(targetX, targetY), w);
		product.addEdge(edge);
		
		product.getNode(sourceX, sourceY).addReachable(product.getNode(targetX, targetY));
		
	}
	
	public void buildUndirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int w){
		UndirectedEdge edge = new UndirectedEdge(product.getNode(sourceX, sourceY),product.getNode(targetX, targetY), w);
		product.addEdge(edge);
		product.getNode(sourceX, sourceY).addReachable(product.getNode(targetX, targetY));
	}
	
	public Graph getProduct(){
		return this.product;
	}
	
}
