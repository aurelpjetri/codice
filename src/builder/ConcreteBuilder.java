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
		
	public void buildDirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException{
		DirectedEdge edge = new DirectedEdge(product.getNodeFromCoordinates(sourceX, sourceY),product.getNodeFromCoordinates(targetX, targetY), width, weight);
		product.addEdge(edge);		
	}
	
	public void buildUndirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException{
		UndirectedEdge edge = new UndirectedEdge(product.getNodeFromCoordinates(sourceX, sourceY),product.getNodeFromCoordinates(targetX, targetY), width, weight);
		product.addEdge(edge);
	}
	
	public Graph getProduct() throws RuntimeException{
		if(product.validateConnectedNodes())	{ //controlla che il grafo sia connesso
			return this.product;
		}
		else{
			throw new  RuntimeException("graph incomplete: some nodes are not linked");
		}
	}
	
}
