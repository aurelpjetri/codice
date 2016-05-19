package behaviors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import graph.Node;
import visitor.Visitor;

public class EvacuateBehavior implements Behavior{

	private List<Node> coreInterestPoints;
	private int id;
	
	public EvacuateBehavior(int id){
		coreInterestPoints = new ArrayList<Node>();
		this.id = id;
	} 
	
	public int getId(){
		return id;
	}
	
	public void addCoreInterestPoint(Node n){
		coreInterestPoints.add(n);
	}
	
	public List<Node> getCoreInterestPoints(){
		return coreInterestPoints;
	}

	public List<Node> getOptionalInterestPoints() {
		return null;
	}

	public void addOptionalInterestPoint(Node n) {
	}
	
	public void accept(Visitor v)throws IOException{
		v.visit(this);
	}
}
