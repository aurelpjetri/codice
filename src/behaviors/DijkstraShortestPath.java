package behaviors;

import grafo.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class DijkstraShortestPath {

//	private final List<Node> nodes;
	private final List<Edge> edges;
	private Set<Node> settledNodes;
	private Set<Node> unsettledNodes;
	private Map<Node, Node> predecessors;
	private Map<Node, Integer> distance;
	
	public DijkstraShortestPath(Graph graph){
//		nodes = graph.getNodes();
		edges = graph.getEdges();
	}
	
	public void execute(Node source){
	    settledNodes = new HashSet<Node>();
	    unsettledNodes = new HashSet<Node>();
	    distance = new HashMap<Node, Integer>();
	    predecessors = new HashMap<Node, Node>();
	    
		unsettledNodes.add(source);
		distance.put(source, 0);
		
		while(unsettledNodes.size() > 0){
			Node node = getMinimum(unsettledNodes);
			settledNodes.add(node);
			unsettledNodes.remove(node);
		}
		
	}
	
	public void findMinimalDistance(Node node){
		List<Node> neighbours = getNeighbours(node);
		for(Node neighbour : neighbours){
			if(getShortestDistance(neighbour) > getShortestDistance(node) + getDistance(node, neighbour)){
				distance.put(neighbour, getShortestDistance(node) + getDistance(node, neighbour));
				unsettledNodes.add(neighbour);
				predecessors.put(neighbour, node);
			}
		}
	}
	
	public List<Node> getNeighbours(Node node){
		List<Node> neighbours = new ArrayList<Node>();
		for(Node n : node.getReachableNodes()){
			if(!isSettled(n)){
				neighbours.add(n);
			}
		}
		return neighbours;
	}
	
	public Node getMinimum(Set<Node> nodes){
		Node minimum = null;
		for(Node node : nodes){
			if(minimum == null){
				minimum = node;
			}
			else if(getShortestDistance(node) < getShortestDistance(minimum)){
				minimum = node;
			}
		}
		return minimum;
	}
	
	public Integer getShortestDistance(Node node){
		Integer d = distance.get(node);
		if(d == null){
			d =Integer.MAX_VALUE;
		}
		return d;
	}
	
	public int getDistance(Node source, Node target){
		for(Edge edge : edges){
			if(edge.getSource().equals(source) && edge.getTarget().equals(target)){
				return edge.getDistance();
			}
		}
		throw new RuntimeException("edge does not exist");
	}
	
	public boolean isSettled(Node node){
		return settledNodes.contains(node);
	}
	
	public List<Node> getPath(Node target){
		List<Node> path = new ArrayList<Node>();
		Node step = target;
		
		if(predecessors.get(step) == null){
			return null;
		}
		
		path.add(step);
		
		while(predecessors.get(step) != null){
			step = predecessors.get(step);
			path.add(step);
		}
		
		Collections.reverse(path);
		
		return path;
		
	}
}
