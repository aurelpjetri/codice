package grafo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GraphTest {

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
		n0 = new RegularNode(0,0,0,5,5);
		n1 = new EntryPoint(1,0,10,5,5);
		n2 = new RegularNode(2,10,10,5,5);
		n3 = new ExitPoint(3,20,0,5,5);

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
	public void testGetNodeInt() {

		assertEquals(n0, graph.getNode(0));
		assertEquals(n1, graph.getNode(1));
		assertEquals(n2, graph.getNode(2));
		assertEquals(n3, graph.getNode(3));
		
	}

	@Test
	public void testGetNodeIntInt() {
		assertEquals(n0, graph.getNodeFromCoordinates(0,0));
		assertEquals(n1, graph.getNodeFromCoordinates(0,10));
		assertEquals(n2, graph.getNodeFromCoordinates(10,10));
		assertEquals(n3, graph.getNodeFromCoordinates(20,0));
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetNodeIntIntExcep(){
		graph.getNodeFromCoordinates(5, 5);
	}
	
	@Test(expected=RuntimeException.class)
	public void testAddEdge(){
		graph.addEdge(new  UndirectedEdge(n0, n1, 5, 3));
	}
	
	@Test
	public void testValidateNodeIntInt(){
		assertEquals(false, graph.validateNodeFromCoordinates(5, 5));
	}
	
	@Test
	public void testValidateGraph(){
		assertEquals(true, graph.validateConnectedNodes());
	}

	@Ignore
	@Test
	public void testToString(){
		String stringa = "";
		assertEquals(stringa, graph.toString());
	}
	
	@Test
	public void testGetNodeExceptionMessage(){
		try{
			graph.getNode(6);
		}
		catch(RuntimeException e){
			String message = "node "+6+" does not exist";
			assertEquals(message, e.getMessage());
		}
	}
	
	
}
