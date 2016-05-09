package builder;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import grafo.*;
import builder.XMLParser;


public class XMLParserTest {

	private XMLParser parser;
	private Graph graph;
	
	
	@Before
	public void setUp() throws JDOMException, IOException  {
		parser = new XMLParser();
		graph = parser.parseDocumentForGraph("/home/aurel/workspace/prototipe/src/example.xml");
		
	}
	
	@Ignore
	@Test
	public void testProduct() throws IOException, JDOMException{
		graph = parser.parseDocumentForGraph("/home/aurel/workspace/prototipe/src/example.xml");
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
			assertEquals(5, e.getWidth());
		}
	}
	

	
}
