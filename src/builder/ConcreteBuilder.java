package builder;

import grafo.*;
import grafo.Edge;

public class ConcreteBuilder {

	private Graph product;
	
	public void buildGraph(){
		product = new Graph();
	}
	
	public void buildRegularNode(int id){
		product.addNode(new RegularNode(id));
	}
	
	public void buildEdge(Node sourceNode, Node targetNode){
		if(product.validateNode(sourceNode) && product.validateNode(targetNode)){
			
			product.addEdge(new Edge(sourceNode, targetNode));
			
			sourceNode.addReachable(targetNode);
		}
		else{
			System.out.println("impossibile aggiungere arco");
		}
	}
	
}
