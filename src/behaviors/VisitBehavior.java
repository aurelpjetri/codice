package behaviors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import grafo.Node;
import visitor.Visitor;

public class VisitBehavior implements Behavior{
	private List<Node> coreInterestPoints;
	private List<Node> optionalInterestPoints;
	private int id;
	
	public VisitBehavior(int id){
		coreInterestPoints = new ArrayList<Node>();
		optionalInterestPoints = new ArrayList<Node>();
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
		return optionalInterestPoints;
	}

	public void addOptionalInterestPoint(Node n) {
		optionalInterestPoints.add(n);
	}
	
	public void accept(Visitor v)throws IOException{
		v.visit(this);
	}
}
