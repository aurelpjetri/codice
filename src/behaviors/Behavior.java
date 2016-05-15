package behaviors;

import java.util.List;

import grafo.Node;

public interface Behavior {
	
	public int getId();

	public List<Node> getCoreIp();
	
	public List<Node> getOptionalIp();
	
	public void addOptionalIp(Node n);

	public void addCoreIp(Node n);
	
}
