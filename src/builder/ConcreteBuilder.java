package builder;

import behaviors.Behavior;
import behaviors.ConcreteBehavior1;
import grafo.DirectedEdge;
import grafo.EntryPoint;
import grafo.ExitPoint;
import grafo.Graph;
import grafo.Node;
import grafo.RegularNode;
import grafo.UndirectedEdge;


public class ConcreteBuilder {

	private Graph product;
	private int lastId;
	// mantenere memoria dell'ultimo id generato
	
	
	public void buildGraph(){
		product = new Graph();
		lastId = 0;
	}
	
	public void buildRegularNode(int x, int y, int w, int h, int r, int s){
		RegularNode.validateNodeParameters(x, y, w, h, r, s);
		product.addNode(new RegularNode(lastId, x, y, w, h, r, s));
		lastId += 1;
	}
	
	public void buildExitPoint(int x , int y, int w, int h, int r, int s){
		ExitPoint.validateNodeParameters(x, y, w, h, r, s);
		product.addNode( new ExitPoint(lastId, x, y, w, h, r, s));
		lastId += 1;
	}
	
	public void buildEntryPoint(int x , int y, int w, int h, int r, int s){
		EntryPoint.validateNodeParameters(x, y, w, h, r, s);
		product.addNode( new EntryPoint(lastId, x, y, w, h, r, s));
		lastId += 1;
	}
		
	public void buildDirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException{
		DirectedEdge edge = new DirectedEdge(product.getNodeFromCoordinates(sourceX, sourceY),product.getNodeFromCoordinates(targetX, targetY), width, weight);
		product.addEdge(edge);		
	}
	
	public void buildUndirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException{
		UndirectedEdge edge = new UndirectedEdge(product.getNodeFromCoordinates(sourceX, sourceY),product.getNodeFromCoordinates(targetX, targetY), width, weight);
		product.addEdge(edge);
	}
	
	
	public void buildConcreteBehavior1(int id){
		Behavior b = new ConcreteBehavior1(id);
		product.addBehavior(b);
	}
	
	public void buildInterestPointOnBehavior(int x, int y, int id, boolean optionalityFlag){
		
		if(optionalityFlag){
			Node n = product.getNodeFromCoordinates(x, y);
			product.getBehaviorFromId(id).addOptionalIp(n);
		}
		else{
			Node n = product.getNodeFromCoordinates(x, y);
			product.getBehaviorFromId(id).addCoreIp(n);
		}
	}
	
	public Graph getProduct() throws RuntimeException{
		//controlla che il grafo sia connesso
		if(product.validateConnectedNodes())	{ 
			return this.product;
		}
		else{
			throw new  RuntimeException("graph incomplete: some nodes are not linked");
		}
	}
	
}
