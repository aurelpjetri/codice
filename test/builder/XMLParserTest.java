package builder;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import behaviors.EvacuateBehavior;
import behaviors.VisitBehavior;
import builder.XMLParser;
import graph.*;


public class XMLParserTest {

	private XMLParser parser;
	private Graph graph;
	
	
	@Before
	public void setUp() throws JDOMException, IOException  {
		parser = new XMLParser();
		graph = parser.parseDocumentForGraph("data/xmls/example3.xml");
		
	}
	

	
	@Test
	public void testEdgesBuilt(){
		
		Node n0 = graph.getNodeFromCoordinates(0, 0);
		Node n1 = graph.getNodeFromCoordinates(0, 10);
		Node n2 = graph.getNodeFromCoordinates(10, 10);
		Node n3 = graph.getNodeFromCoordinates(20, 0);
	
		boolean result1 = graph.validateEdge(n0, n1);
		boolean result2 = graph.validateEdge(n0, n3);
		boolean result3 = graph.validateEdge(n2, n3);
		boolean result4 = graph.validateEdge(n1, n2);
		
		assertEquals(true, result1);
		assertEquals(true, result2);
		assertEquals(true, result3);
		assertEquals(true, result4);

	}
	
	@Ignore
	@Test
	public void testEdgesType(){
		boolean result = true;
		for(Edge edge : graph.getEdges()){
			if(!(edge instanceof UndirectedEdge)){
				result = false;
			}
		}
		
		assertEquals(true, result);
	}
	
	@Test
	public void testNodesType(){
		Node n0 = graph.getNodeFromCoordinates(0, 0);
		Node n1 = graph.getNodeFromCoordinates(0, 10);
		Node n2 = graph.getNodeFromCoordinates(10, 10);
		Node n3 = graph.getNodeFromCoordinates(20, 0);
		
		boolean result0 = n0 instanceof RegularNode;
		boolean result1 = n1 instanceof EntryPoint;
		boolean result2 = n2 instanceof RegularNode;
		boolean result3 = n3 instanceof ExitPoint;

		assertEquals(true, result0);
		assertEquals(true, result1);
		assertEquals(true, result2);
		assertEquals(true, result3);
			
	}
	
	@Test
	public void testCapacity(){
		for(Node n : graph.getNodes()){
			assertEquals(125, n.getCapacity());
		}
	}
	
	@Test
	public void testEdgesWidth(){
		for(Edge e : graph.getEdges()){
			assertEquals(3, e.getWidth());
		}
	}	

	@Test
	public void testStatus(){
		HashMap<Integer, Integer> stato = new HashMap<>();
		
		stato.put(0, 2);
		stato.put(1, 3);
		
		assertEquals(stato, graph.getNodeFromCoordinates(0, 0).getState());
		
		
		HashMap<Integer, Float> rate = new HashMap<>();
		
		rate.put(0, (float)0.3);
		rate.put(1, (float)0.7);
		
		assertEquals(rate, graph.getNodeFromCoordinates(0, 10).getGenerationRate());
	}
	
	@Test
	public void testBehaviors(){
		assertEquals(2, graph.getBehaviors().size());
		assertEquals(1, graph.getBehaviorFromId(0).getCoreInterestPoints().size());
		assertEquals(true, graph.getBehaviorFromId(0) instanceof VisitBehavior );
		assertEquals(true, graph.getBehaviorFromId(1) instanceof EvacuateBehavior );
		assertEquals(graph.getNodeFromCoordinates(10, 10), graph.getBehaviorFromId(1).getCoreInterestPoints().get(0) );
	}
	
	@Test
	public void testBehaviorsOnExmple3(){
		graph = parser.parseDocumentForGraph("data/xmls/example3.xml");
		assertEquals(2, graph.getBehaviors().size());
		assertEquals(true, graph.getBehaviorFromId(0) instanceof VisitBehavior );
		assertEquals(true, graph.getBehaviorFromId(1) instanceof EvacuateBehavior );
	}

	
}
