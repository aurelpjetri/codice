package builder;

import grafo.*;


public class ConcreteBuilder {

	private Graph product;
	private int lastId;
	// mantenere memoria dell'ultimo id generato
	
	
	public void buildGraph(){
		product = new Graph();
		lastId = 0;
	}
	
	public void buildRegularNode(int x, int y, int w, int h, int r){
		RegularNode.validateNodeParameters(x, y, w, h, r);
		product.addNode(new RegularNode(lastId, x, y, w, h, r));
		lastId += 1;
	}
	
	public void buildExitPoint(int x , int y, int w, int h, int r){
		ExitPoint.validateNodeParameters(x, y, w, h, r);
		product.addNode( new ExitPoint(lastId, x, y, w, h, r));
		lastId += 1;
	}
	
	public void buildEntryPoint(int x , int y, int w, int h, int r){
		EntryPoint.validateNodeParameters(x, y, w, h, r);
		product.addNode( new EntryPoint(lastId, x, y, w, h, r));
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
