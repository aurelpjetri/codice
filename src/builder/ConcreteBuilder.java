package builder;

import grafo.*;
import grafo.Edge;
import java.util.List;
import java.util.ArrayList;

public class ConcreteBuilder {

	private Graph product;
	private List<Integer> ids;
	
	public void buildGraph(){
		product = new Graph();
		ids = new ArrayList<Integer>();
	}
	
	public void buildRegularNode(int x, int y){
		ids.add(1);
		int id = ids.size();
		product.addNode(new RegularNode(id, x, y));
	}
	
	public void buildExitPoint(int x , int y){
		ids.add(1);
		int id = ids.size();
		product.addNode( new ExitPoint(id, x, y));
	}
	
	public void buildEntryPoint(int x , int y){
		ids.add(1);
		int id = ids.size();
		product.addNode( new EntryPoint(id, x, y));
	}
	
	
	public void buildEdge (int sourceX, int sourceY, int targetX, int targetY){
		Edge edge = new Edge(product.getNode(sourceX, sourceY),product.getNode(targetX, targetY));
		product.addEdge(edge);
		
	}
	
	
	public Graph getProduct(){
		return this.product;
	}
	
}
