package behaviors;

import grafo.*;
import builder.*;

public class Evacuate implements Behavior{

	private Graph behaviorGraph;
	private ConcreteBuilder behaviorBuilder;
	private Graph completeGraph;
	private int myExitPoint;
	private DijkstraShortestPath shortestPath;
	
	Evacuate(Graph g, int exitP){
		completeGraph = g;
		behaviorBuilder = new ConcreteBuilder();
		behaviorGraph = buildBehaviorGraph();
		myExitPoint = exitP;
		shortestPath = new DijkstraShortestPath(g);
	}
	
	public Graph buildBehaviorGraph(){
		//in teoria dovrebbe essere calcola percorso minimo verso uscita
		//e costruisce il grafo escludendo gli archi all'indietro
		behaviorBuilder.buildGraph();
		for(Node n : completeGraph.getNodes()){
			behaviorBuilder.buildRegularNode(n.getId());
		}
		for(Edge e : completeGraph.getEdges()){
			behaviorBuilder.buildEdge(e.getSource().getId(), e.getDest().getId());
		}
		return behaviorBuilder.getProduct();
	}
	
	public Graph getBehaviorGraph(){
		return this.behaviorGraph;
	}
	
}
