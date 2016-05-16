package builder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Before;
import org.junit.Test;

import behaviors.EvacuateBehavior;
import behaviors.VisitBehavior;
import grafo.Graph;

public class XMLParserTest2 {

	private XMLParser parser;
	private Graph graph;
	
	
	@Before
	public void setUp() throws JDOMException, IOException  {
		parser = new XMLParser();
	}
	
	
	
	@Test
	public void testBehaviors(){
		graph = parser.parseDocumentForGraph("data/example.xml");
		assertEquals(2, graph.getBehaviors().size());
		assertEquals(1, graph.getBehaviorFromId(0).getCoreInterestPoints().size());
		assertEquals(true, graph.getBehaviorFromId(0) instanceof VisitBehavior );
		assertEquals(true, graph.getBehaviorFromId(1) instanceof EvacuateBehavior );
		assertEquals(graph.getNodeFromCoordinates(10, 10), graph.getBehaviorFromId(1).getCoreInterestPoints().get(0) );
	}
	
	@Test
	public void testBehaviorsOnExmple3(){
		graph = parser.parseDocumentForGraph("data/example3.xml");
		assertEquals(2, graph.getBehaviors().size());
		assertEquals(true, graph.getBehaviorFromId(0) instanceof VisitBehavior );
		assertEquals(true, graph.getBehaviorFromId(1) instanceof EvacuateBehavior );
	}

}
