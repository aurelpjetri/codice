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
	
	public void buildEdge (int sourceId, int targetId){
		if(product.validateNodeFromid(sourceId) && product.validateNodeFromid(targetId)){
			try{
				product.addEdge(new Edge(product.getNode(sourceId), product.getNode(targetId)));
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
//	public void buildEdge(Node sourceNode, Node targetNode){
//		if(product.validateNode(sourceNode) && product.validateNode(targetNode)){
//			
//			product.addEdge(new Edge(sourceNode, targetNode));
//			
//			sourceNode.addReachable(targetNode);
//		}
//		else{
//			System.out.println("impossibile aggiungere arco");
//		}
//	}
	
	public Graph getProduct(){
		return this.product;
	}
	
}
