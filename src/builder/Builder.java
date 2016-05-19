package builder;

import java.util.HashMap;

import graph.Graph;

public interface Builder {
	
	
	public void buildRegularNode(int x, int y, int nWeight, int nHeight, int radius, HashMap<Integer,Integer> state);
	
	public void buildExitPoint(int x , int y, int w, int h, int r, HashMap<Integer,Integer> s, HashMap<Integer,Float> sinkingRate);
	
	public void buildEntryPoint(int x , int y, int w, int h, int r, HashMap<Integer,Integer> s, HashMap<Integer,Float> generationRate);
		
	public void buildDirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException;
	
	public void buildUndirectedEdge (int sourceX, int sourceY, int targetX, int targetY, int width, int weight) throws RuntimeException;
	
	public void buildVisitBehavior(int id);
	
	public void buildEvacuateBehavior(int id);
	
	public void buildInterestPointOnBehavior(int x, int y, int id, boolean optionalityFlag);
	
	public Graph getProduct() throws RuntimeException;
	
	
	
}
