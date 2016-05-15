package behaviors;

import java.util.ArrayList;
import java.util.List;

import grafo.Node;

public class ConcreteBehavior1 implements Behavior{
	private List<Node> cip;
	private List<Node> oip;
	private int id;
	
	public ConcreteBehavior1(int id){
		cip = new ArrayList<Node>();
		oip = new ArrayList<Node>();
		this.id = id;
	} 
	
	public int getId(){
		return id;
	}
	
	public void addCoreIp(Node n){
		cip.add(n);
	}
	
	public List<Node> getCoreIp(){
		return cip;
	}

	public List<Node> getOptionalIp() {
		return oip;
	}

	public void addOptionalIp(Node n) {
		oip.add(n);
	}
	
	
}
