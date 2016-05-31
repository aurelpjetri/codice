package builder;

import java.util.HashMap;

import behaviors.EvacuateBehavior;
import behaviors.VisitBehavior;
import graph.DirectedEdge;
import graph.EntryPoint;
import graph.ExitPoint;
import graph.Graph;
import graph.Node;
import graph.RegularNode;
import graph.UndirectedEdge;


public class NetLogoGraphBuilder implements Builder{

	private Graph product;
	private int lastId;
	// mantenere memoria dell'ultimo id generato
	
	
	public void buildGraph(){
		product = new Graph();
		lastId = 0;
	}
	
	public void buildRegularNode(int x, int y, int nWeight, int nHeight, int radius, HashMap<Integer,Integer> state){
		RegularNode.validateNodeParameters(x, y, nWeight, nHeight, radius, state);
		product.addNode(new RegularNode(lastId, x, y, nWeight, nHeight, radius, state));
		lastId += 1;
	}
	
	public void buildExitPoint(int x , int y, int w, int h, int r, float exitRate, HashMap<Integer,Integer> s, HashMap<Integer,Float> sinkingRate){
		ExitPoint.validateNodeParameters(x, y, w, h, r, exitRate, s, sinkingRate);
		product.addNode( new ExitPoint(lastId, x, y, w, h, r, exitRate, s, sinkingRate));
		lastId += 1;
	}
	
	public void buildEntryPoint(int x , int y, int w, int h, int r, float entryRate, HashMap<Integer,Integer> s, HashMap<Integer,Float> generationRate, int entryLimit){
		EntryPoint.validateNodeParameters(x, y, w, h, r, entryRate, s,  generationRate);
		product.addNode( new EntryPoint(lastId, x, y, w, h, r, entryRate, s, generationRate, entryLimit));
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
	
	public void buildVisitBehavior(int id){
		product.addBehavior(new VisitBehavior(id));
	}
	
	public void buildEvacuateBehavior(int id){
		product.addBehavior(new EvacuateBehavior(id));
	}
	
	public void buildInterestPointOnBehavior(int x, int y, int id, boolean optionalityFlag){
		
		if(optionalityFlag){
			Node n = product.getNodeFromCoordinates(x, y);
			product.getBehaviorFromId(id).addOptionalInterestPoint(n);
		}
		else{
			Node n = product.getNodeFromCoordinates(x, y);
			product.getBehaviorFromId(id).addCoreInterestPoint(n);
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
