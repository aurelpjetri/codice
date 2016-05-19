package grafo;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import graph.Edge;
import graph.EntryPoint;
import graph.ExitPoint;
import graph.Graph;
import graph.Node;
import graph.RegularNode;
import graph.UndirectedEdge;

public class GraphValidationTest {
	
	private Graph graph;
	private Node n0;
	private Node n1;
	private Node n2;
	private Node n3;
	
	private Edge e1;
	private Edge e2;
	private Edge e3;
	private Edge e4;

	
	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		
		HashMap<Integer, Integer> stato = new HashMap<>();
		stato.put(0, 2);
		stato.put(1, 3);
		
		HashMap<Integer, Float> rate = new HashMap<>();
		
		stato.put(0, 2);
		stato.put(1, 3);
		rate.put(0, (float)0.3);
		rate.put(1, (float)0.7);
		n0 = new RegularNode(0,0,0,5,5,5,stato);
		n1 = new EntryPoint(1,0,10,5,5,5, stato, rate);
		n2 = new RegularNode(2,10,10,5,5,5, stato);
		n3 = new ExitPoint(3,20,0,5,5,5, stato, rate);

		
		e1 = new  UndirectedEdge(n0, n1, 5, 3);
		e2 = new  UndirectedEdge(n1, n2, 5, 3);
		e3 = new  UndirectedEdge(n2, n3, 5, 3);
		e4 = new  UndirectedEdge(n0, n3, 5, 3);
		
		graph.addNode(n0);
		graph.addNode(n1);
		graph.addNode(n2);
		graph.addNode(n3);
		
		graph.addEdge(e1);
		graph.addEdge(e2);
		graph.addEdge(e3);
		graph.addEdge(e4);
		
	}

	@Test
	public void testValidateConnectedNodes() {
		assertEquals(false, graph.validateConnectedNodes());
		System.out.println(graph.toString());
		
	}

}
